<!DOCTYPE html>
<html>
<body>

	<canvas id="myCanvas" width="500" height="300"
		style="border: 1px solid #c3c3c3;">
Your browser does not support the HTML5 canvas tag.
</canvas>






	<P>
		<INPUT TYPE="Button" VALUE=" START " onClick="drawOnCanvas()">
	</P>

	<P>
		<INPUT TYPE="Button" VALUE=" STOP " onclick="stopTimer()">
	</P>

 
</body>


<script>
	function drawOnCanvas() {

		var canvas = document.getElementById("myCanvas");
		if (canvas.getContext) {

			canvas_context = canvas.getContext("2d");
			doTimer();
		}

	}

	function moveBox() {

		if (x_pos >= 500) {
			x_pos = -150;
		}

		canvas_context.clearRect(0, 0, 500, 300);
		

		var text = 'Hello kiwi!';
		canvas_context.font = "30pt Verdana";
		canvas_context.fillText(text, x_pos, 50);
		x_pos = x_pos + 10;

	}

	function doTimer() {
		x_pos = 0;
		timerID = setInterval(moveBox, 50);
	}

	function stopTimer() {
		clearInterval(timerID);
	}
</script>

</body>
</html>
