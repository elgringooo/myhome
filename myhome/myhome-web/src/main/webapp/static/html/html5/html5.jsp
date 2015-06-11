<!DOCTYPE html>
<html lang="fr">

<head>
<!-- En-tête du document  -->
<title>Html5</title>
</head>
<body>
	<br>
	<div>
		<h2>
			Welcome... <br>
		</h2>
	</div>



	<section>

		<section>
			<h2>Balise Datalist</h2>
			<!-- L'élément <datalist> spécifie une liste prédéfinis d'options pour un élément <input>.  -->
			<input list="navigateur" />
			<datalist id="navigateur">
				<option value="Internet Explorer">
				<option value="Firefox">
				<option value="Chrome">
				<option value="Opera">
				<option value="Safari">
			</datalist>
		</section>

		<section>
			<h2>Balise keygen</h2>
			<!-- La balise keygen spécifie un champ générateur de clés dans un formulaire. -->
			<form action="demo_keygen.php" method="get">
				Username: <input type="text" name="nom_utilisateur" /> Encryption:
				<keygen name="security" />
				<input type="submit" />
			</form>
		</section>
		<section>
			<!-- L"élément <output> représente le résultat d"un calcul (réalisé par un script par exemple). -->
			<h2>Balise Output</h2>
			<form oninput="x.value=parseInt(a.value)+parseInt(b.value)">
				0 <input type="range" name="a" value="50" />100 +<input
					type="number" name="b" value="50" /> =
				<output name="x" for="a b"></output>
			</form>
		</section>



		<section>
			<h2>les inputs email, tel, url, date, color, time etc..</h2>
			<form>
				<div>
					<label for="champ1">Votre adresse e-mail</label> <input id="champ1"
						type="email" required="">
				</div>
				<div>
					<label for="champ2">Votre tel</label> <input id="champ2" type="tel"
						required=""
						pattern="^((\+\d{1,3}(-| )?\(?\d\)?(-| )?\d{1,5})|(\(?\d{2,6}\)?))(-| )?(\d{3,4})(-| )?(\d{4})(( x| ext)\d{1,5}){0,1}$">
				</div>
				<div>
					<label for="website">Votre url</label> <input type="url"
						value="http://" name="website">
				</div>
				<div>
					<label for="date">Votre date de naissance</label> <input id="date"
						type="date" name="date">
				</div>


				<div>
					<label for="color">Color</label> <input id="color" type="color"
						name="color">
				</div>

				<div>
					<label for="number">Number</label> <input id="number" type="number"
						name="number">
				</div>


				<div>
					<label for="time">time</label> <input id="time" type="time"
						name="time">
				</div>

				<div>
					<label for="week">week</label> <input id="week" type="week"
						name="week">
				</div>

				<button>Tayst</button>
			</form>

		</section>


		<section>

			<div>
				<h2>Placeholder</h2>
				<label for="chanteur">Votre chanteur préféré ?</label> <input
					id="chanteur" type="text" placeholder="Nom d'un chanteur"
					name="chanteur">
			</div>



			<div>
				<h2>Audio</h2>
				<audio controls="controls" preload="none">
					<source src="music.mp3" type="audio/mp3" />
					Votre navigateur n'est pas compatible
				</audio>


			</div>
			<div>
				<h2>Video</h2>
				<video controls preload>
					<source src="maVideo.ogv" type="video/ogg; codecs='vorbis, theora'" />
					<source src="maVideo.ogv"
						type="video/mp4; 'codecs='avc1.42E01E, mp4a.40.2'" />
					<p>
						Votre navigateur ne prend pas la video en charge <a
							href="maVideo.mp4">Telecharge la.</a>
					</p>
				</video>
			</div>


			<h2>Contenu editable par exemple list de taches</h2>
			<ul contenteditable="true">
				<li>Acheter du pain</li>
				<li>Emener les enfants à l'école</li>
				<li>Oublier de ramener les enfants de l'école</li>
			</ul>


			<h2>Balise figure</h2>
			<figure>
				<img
					src="http://www.google.fr/url?source=imglanding&ct=img&q=https://addons.cdn.mozilla.net/user-media/addon_icons/375/375984-64.png?modified=1368573287&sa=X&ei=owNbVajsJsaAU9eygYAP&ved=0CAkQ8wc&usg=AFQjCNExgik_fwwA2syEwuJOBbce7uCINQ"
					alt="description de l'image" />
				<figcaption>
					<p>Legende de l'image, ou texte relatif à l'image</p>
				</figcaption>
			</figure>


			<h2>Attribut required</h2>
			<form method="post" action="">
				<label for="nomChamp"> Votre prénom : </label> <input type="text"
					id="nomChamp" name="nomChamp" placeholder="41 Mag" required>
				<button type="submit">Envoyer</button>
			</form>
			<h2>Attribut autofocus</h2>
			<form method="post" action="">
				<div>
					<label for="nom"> Nom : </label><input name="nom"
						autofocus="autofocus" />
				</div>
				<div>
					<label for="prenom"> Prenom prénom : </label> <input name="prenom"
						autofocus="autofocus" />
				</div>


				<button type="submit">Envoyer</button>
			</form>



			<h2>Attribut patterns</h2>
			<form action="" method="post">
				<label for="nomChamp">Créer un pseudo : </label> <input type="text"
					name="username" id="username" placeholder="4 <> 10"
					pattern="[A-Za-z]{4,10}" autofocus required>
				<button type="submit">Go</button>
			</form>
		</section>

	</section>








</body>

</html>
