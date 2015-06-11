<!DOCTYPE html>
<html>
<body>

	<canvas id="myCanvas" width="500" height="300"
		style="border: 1px solid #c3c3c3;">
Your browser does not support the HTML5 canvas tag.
</canvas>




	<canvas id="myCanvas2" width="500" height="300"
		style="border: 1px solid #c3c3c3;">
Your browser does not support the HTML5 canvas tag.
</canvas>



	<canvas id="myCanvas3" width="500" height="300"
		style="border: 1px solid #c3c3c3;">
Your browser does not support the HTML5 canvas tag.
</canvas>





	<canvas id="myCanvas4" width="500" height="300"
		style="border: 1px solid #c3c3c3;">
Your browser does not support the HTML5 canvas tag.
</canvas>


	<P>
		<INPUT TYPE="Button" VALUE=" START " onClick="drawOnCanvas()">
	</P>

	<P>
		<INPUT TYPE="Button" VALUE=" STOP " onclick="stopTimer()">
	</P>

</BODY>


<script>
	var c = document.getElementById("myCanvas");
	var ctx = c.getContext("2d");
	ctx.fillStyle = "#FF0000";
	ctx.fillRect(0, 0, 150, 75);

	//Voile du bateau
	ctx.beginPath(); // Début du chemin
	ctx.moveTo(150, 80); // Le tracé part du point 150,80
	ctx.lineTo(300, 230); // Un segment est ajouté vers 300,230
	ctx.lineTo(150, 230); // Un segment est ajouté vers 150,230
	ctx.closePath(); // Fermeture du chemin
	ctx.fillStyle = "lightblue"; // Définition de la couleur de remplissage
	ctx.fill(); // Remplissage du dernier chemin tracé

	// Coque du bâteau
	ctx.beginPath(); // Début d'un autre chemin
	ctx.moveTo(50, 250);
	ctx.lineTo(100, 300);
	ctx.lineTo(250, 300);
	ctx.lineTo(300, 250);
	ctx.fillStyle = "peru";
	ctx.strokeStyle = "sienna"; // Définition de la couleur de contour
	ctx.lineWidth = 5; // Définition de la largeur de ligne
	ctx.fill(); // Application du remplissage
	ctx.stroke(); // Application du contour

	// Mât
	ctx.beginPath();
	ctx.lineJoin = "round";
	ctx.lineCap = "round";
	ctx.moveTo(140, 50);
	ctx.lineTo(140, 250);
	ctx.lineWidth = 10;
	ctx.stroke();

	c = document.getElementById("myCanvas2");
	ctx = c.getContext("2d");
	ctx.lineWidth = 5;

	//Visage
	ctx.beginPath();
	ctx.arc(150, 150, 100, 0, Math.PI * 2, true);
	ctx.strokeStyle = "coral";
	ctx.fillStyle = "bisque";
	ctx.fill();
	ctx.stroke();

	//Bouche
	ctx.beginPath();
	ctx.arc(150, 150, 60, 0, Math.PI, false);
	ctx.strokeStyle = "red";
	ctx.stroke();

	//Yeux
	ctx.beginPath();
	ctx.strokeStyle = "#369";
	ctx.fillStyle = "#c00";
	ctx.arc(180, 130, 15, 0, Math.PI * 2, false);
	ctx.stroke();
	ctx.beginPath();
	ctx.arc(120, 130, 15, 0, Math.PI * 2, false);
	ctx.stroke();

	c = document.getElementById("myCanvas4");
	ctx = c.getContext("2d");
	var text = 'Hello kiwi!';
	ctx.font = "30pt Verdana";
	ctx.textAlign = "left";
	ctx.textBaseline = "top";
	var textPxLength = ctx.measureText(text);
	ctx.fillStyle = "darkgreen";
	ctx.fillText(text, 25, 50);
	ctx.fillStyle = "darkorange";
	ctx.fillText("width: " + Math.round(textPxLength.width) + "px", 25, 100);
	
	
	
</script>

</body>
</html>
