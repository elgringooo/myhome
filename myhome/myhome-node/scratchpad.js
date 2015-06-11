// valeur initiale définie vaut undefined.
var a;
console.log('La valeur a est ' + a); // le log contient "La valeur de a est
// undefined"

// La valeur undefined se comporte comme le booléen false
var input;
if (!input) {
	console.log('La valeur est  undefined');
}

/**
 * TYPE
 */
// Type en javascript: null, undefined, string, nombre, boolean et objet
// undefined vs null . Null est utilisé pour un objet. Undefined pour toute
// variable non definie.
var TestVar;
console.log(TestVar); // shows undefined
console.log(typeof TestVar); // shows undefined

var TestVar = null;
console.log(TestVar); // shows null
console.log(typeof TestVar); // shows object

var myfloat = parseFloat("3.3");
console.log(myfloat);

var myint = parseInt("3.3");
console.log(myint);

/**
 * TABLEAU
 */

// Tableau literal est aussi un objet Array
var cafeTab = [ "Brésilien", "Colombien", "Kona" ];
var mytab = new Array();
mytab[0] = "Test";
console.log(cafeTab[2]);
console.log(mytab[0]);

for ( var i in cafeTab) {
	console.log(cafeTab[i]);
}

// Map ou tableau associatif . tableau est une map avec des clés numeriques
var mymap = {
	"key1" : "value1",
	"key2" : "value2",
	"key3" : "value3"
}
for ( var key in mymap) {
	console.log(key + " -->" + mymap[key]);
}

console.log(carre("2"));

/**
 * FONCTION
 */

// declaration
function carre(nombre) {
	return nombre * nombre;
}

// changement par ref d'un objet

var voiture = {
	fabricant : "Ford",
	modèle : "Focus",
	année : 2006
};

changeFabriquant(voiture);
console.log(voiture.fabricant)


function changeFabriquant(monObjet) {
	monObjet.fabricant = "Nissan";
}

function saluer(utilisateur) {
	return 'Bonjour ' + utilisateur;
}