var http = require('http');


var server = http.createServer(function(req, res) {

    res.writeHead(200, {"Content-Type": "text/html"});

    res.write('<!DOCTYPE html>'+

'<html>'+

'    <head>'+

'        <meta charset="utf-8" />'+


'        <script type="text/javascript" src="HelloWorld.js"></script>'+


'        <title>Ma page Node.js !</title>'+

'    </head>'+ 

'    <body>'+

'       <p>Voici un paragraphe <strong>HTML</strong> !</p>'+

'    </body>'+

'</html>');

    res.end();

});
server.listen(8085);