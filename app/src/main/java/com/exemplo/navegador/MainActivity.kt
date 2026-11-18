val webView = findViewById<WebView>(R.id.webView)

// 1. Habilita o JavaScript
webView.settings.jsEnabled = true

// 2. Garante que os links abram dentro do WebView
webView.webViewClient = WebViewClient()

// 3. Exemplo de tratamento da URL digitada
var urlDigitada = "facebook.com.br" // Substitua pelo texto que o usuário digitou

if (!urlDigitada.startsWith("http://") && !urlDigitada.startsWith("https://")) {
    if (urlDigitada.contains(".")) {
        urlDigitada = "https://$urlDigitada"
    } else {
        urlDigitada = "https://www.google.com/search?q=$urlDigitada"
    }
}

webView.loadUrl(urlDigitada)
