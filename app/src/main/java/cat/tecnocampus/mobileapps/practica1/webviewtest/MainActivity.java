package cat.tecnocampus.mobileapps.practica1.webviewtest;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebView wv = findViewById(R.id.web_view);
        //wv.loadUrl("https://www.tecnocampus.cat");

        WebSettings ws = wv.getSettings();
        ws.setJavaScriptEnabled(true);

        wv.addJavascriptInterface(new WebAppInterface(this), "Android");

        wv.loadUrl("file:///android_asset/index.html"); /*Path relatiu perquè busqui
        a la carpeta assets de l'aplicació actual. Resol el directori dinàmicament, podem
        canviar id de l'aplicació, etc., no influirà. Obligatòriament hem d'incloure una
        carpeta assets, amb aquest nom exacte, perquè ho detecti. */
    }

    public class WebAppInterface{
        Context mContext;

        WebAppInterface(Context c){
            mContext = c;
        }

        @JavascriptInterface
        public void showToast(String toastText){
            Toast.makeText(mContext, toastText, Toast.LENGTH_LONG).show();
        }
    }
}
