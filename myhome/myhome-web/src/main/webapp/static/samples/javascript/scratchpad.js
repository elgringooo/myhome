/**
 * STRUCTURE DE DONNEEE 
 */
/**
 * VARIABLE
 */


console.log('\n  #### VARIABLE ####');
// valeur initiale definie vaut undefined.
var a;
console.log('La valeur a est ' + a); // le log contient "La valeur de a est
// undefined"
// La valeur undefined se comporte comme le booleen false
var input;
if (!input) {
  console.log('La valeur est  undefined');
}
/**
 * TYPE
 */
// Type en javascript: null, undefined, string, nombre, boolean et objet
// undefined vs null . Null est utilise pour un objet. Undefined pour toute
// variable non definie.

var TestVar;
console.log(TestVar); // shows undefined
console.log(typeof TestVar); // shows undefined
var TestVar = null;
console.log(TestVar); // shows null
console.log(typeof TestVar); // shows object
var myfloat = parseFloat('3.3');
console.log(myfloat);
var myint = parseInt('3.3');
console.log(myint);
/**
 * EGALITE
 */
console.log('\n  #### EGALITE ####');
// L'egalite stricte avec === : L'egalite stricte compare deux valeurs et teste
// leur egalite. Aucune des valeurs n'est convertie implicitement en une autre
// valeur avant que la comparaison soit effectuee.
var num = 0;
var obj = new String('0');
var str = '0';
var b = false;
console.log(num === num); // true
console.log(obj === obj); // true
console.log(str === str); // true
console.log(num === obj); // false
console.log(num === str); // false
console.log(obj === str); // false
console.log(null === undefined); // false
console.log(obj === null); // false
console.log(obj === undefined); // false
// L'egalite faible avec ==: Le test d'egalite faible compare deux valeurs après
// les avoir converties en valeurs d'un même type.
var num = 0;
var obj = new String('0');
var str = '0';
var b = false;
console.log(num == num); // true
console.log(obj == obj); // true
console.log(str == str); // true
console.log(num == obj); // true
console.log(num == str); // true
console.log(obj == str); // true
console.log(null == undefined); // true
// Les deux assertions qui suivent sont fausses
// sauf dans certains cas exceptionnels
console.log(obj == null);
console.log(obj == undefined);
/**
 * TABLEAU
 */
console.log('\n  #### TABLEAU ####');
// Declaration literal
var cafeTab = [
  'Bresilien',
  'Colombien',
  'Kona'
];

// rajouter des infos dansle tableau
cafeTab.push('Francais');

for (var i in cafeTab) {
  console.log(cafeTab[i]);
}
// Declaration par objet

var mytab = new Array('Test1', 'test3');
mytab[2] = 'Test3';
for (var i in mytab) {
  console.log(mytab[i]);
}




/**
 * TABLEAU ASSOCIATIF
 */

console.log('\n  #### TABLEAU ASSOCIATIF ####');
// Map ou tableau associatif . tableau est une map avec des cles numeriques
var tabassoc = new Array(); // declaration d'un tableau vide
tabassoc['nom'] = 'Moran';
tabassoc['prenom'] = 'Bob';
for (var key in tabassoc) {
  console.log(key + ' -->' + tabassoc[key]);
}
/**
 * JSON
 */

console.log('\n #### JSON ####');
var mymap = {
  'key1': 'value1',
  'key2': 'value2',
  'key3': 'value3'
}
for (var key in mymap) {
  console.log(key + ' -->' + mymap[key]);
}
/**
 * FONCTIONS
 */

console.log('\n #### FUNCTIONS: DEF ####');
// declaration
function carre(nombre) {
  return nombre * nombre;
}
console.log(carre(2));
/**
 * FONCTIONS ANONYMES
 * 
 */
var r = function () {
  console.log('BOnjour');
};
r();
var x = function (y, z) {
  return y + z;
}
console.log('resultat ' + x(2, 3));

// changement par ref d'un objet
var voiture = {
  fabricant: 'Ford',
  modele: 'Focus',
  annee: 2006
};
function changeFabriquant(monObjet) {
  monObjet.fabricant = 'Nissan';
}
changeFabriquant(voiture);
console.log(voiture.fabricant);
/**
 * CLOSURE: Les fermetures, ou closures en anglais, sont des fonctions qui
 * utilisent des variables libres. Autrement dit, la fonction definie dans la
 * fermeture se « souvient » de l'environnement dans lequel elle a ete creee.
 */
console.log('\n #### FUNCTIONS: CLOSURE ####');
function faireAddition(x) {
  // Closure
  return function (y) {
    return x + y;
  };
}
var ajout5 = faireAddition(5);
var ajout10 = faireAddition(10);
console.log(ajout5(2)); // 7
console.log(ajout5(3)); // 8
console.log(ajout10(2)); // 12
/**
 * Immediately Invoked Function Expression (IIFE): ce sont des fonctions qui
 * s'executent toutes seules=> Expression de fonction immediatement invoquee
 */
console.log('\n #### FUNCTIONS: IIFE ####');
(function () {
  var test = 1;
  console.log('testtttttttt' + test);
}) ();
/**
 * FUNCTION WITH CALLBACK
 */
console.log('\n #### FUNCTIONS: CALLBACK ####');
function functionwithcallback(arg1, arg2, callback) {
  callback(arg1 + ' -->' + arg2);
}
functionwithcallback('data1', 'data2', function (data) {
  console.log(data);
});
/**
 * 
 * CLOSURE + IIFF = Module pattern
 * 
 */
console.log('\n #### FUNCTIONS: CLOSURE + IIFF = Module pattern ####');
var compteur = (function () {
  var compteurPrive = 0;
  // private method
  function changeValeur(val) {
    compteurPrive += val;
  }
  function valeur() {
    return compteurPrive;
  }
  function decrement() {
    changeValeur( - 1);
  }
  function increment() {
    changeValeur(1);
  }
  // public method

  return {
    increment: increment,
    decrement: decrement,
    valeur: valeur
  };
}) ();
console.log(compteur.valeur()); /* Affiche 0 */
compteur.increment();
compteur.increment();
console.log(compteur.valeur()); /* Affiche 2 */
compteur.decrement();
console.log(compteur.valeur()); /* Affiche 1 */
var MyModule = (function () {
  var options;
  // private methods
  function myprivate() {
    options.age++;
    return options.age;
  }
  // public methods

  function init(myoptions) {
    options = myoptions;
    return 'initialized ' + options.name + ' =>' + options.age;
  }
  function mymethod() {
    return myprivate();
  }
  // Returns JSON object contains public method

  return {
    init: init,
    mymethod: mymethod
  };
}) ();
var options = {
  'name': 'gege',
  'age': 35
};
console.log(MyModule.init(options));
console.log(MyModule.mymethod());
console.log(MyModule.mymethod());
/**
 * OBJETS EN JAVASCRIPT
 */
console.log('\n #### OBJECT: JSON ####');
// 1ere method: la declaration inline (JSON)
var myObject = {
  a: 1,
  b: 2,
  sum: function () {
    return this.a + this.b;
  }
};
console.log(myObject.a);
console.log(myObject.sum());
console.log('\n #### OBJECT: CONSTRUCTOR ####');
// 2eme methode: constructeur autodeclarant
var Person = function (options) {
  // Private name
  var m_name = options.name;
  // Private age
  var m_age = options.age;
  // Private method
  var calcAgeInDogYears = function () {
    return m_age / 7;
  };
  // Begin Public Section
  return {
    // Name acceesor
    getName: function () {
      return m_name;
    },
    // Name mutator
    setName: function (name) {
      m_name = name;
    },
    // Age accessor
    getAge: function () {
      return m_age;
    },
    // Age mutator
    setAge: function (age) {
      this.m_age = age;
    },
    // Get person's age in dog years
    getAgeInDogYears: function () {
      return calcAgeInDogYears();
    }
  };
};
var p1 = new Person(options);
console.log(p1.getAgeInDogYears());
/**
 * Object Clone
 */
var p2 = Object.create(p1);
// p est un objet heritant de o
console.log(p2.getAgeInDogYears());
/**
 * Use prototype to add functionnality to object
 */
console.log('\n #### OBJECT: USE PROTOTYPE ####');
var Customer = function (name) {
  this.name = name;
}
Customer.prototype.sayHello = function () {
  console.log('Hello, I\'m ' + this.name);
};
var cust = new Customer('Gerald');
cust.sayHello();
// Redefine String object with prototype
String.prototype.printLength = function () {
  console.log(this.length);
}
'fdfsdfsgfgfd'.printLength();
// Redefine A object User with prototype// Definir un objet en utilisant
// prototype permet de ne charger en memoire qu'une seule partie de l'objet.
// moins gourmant que si on definit les fonction dans la classe car chaque
// nouvel instance d objet , les functions sont reinstanciees egalement.
console.log('\n #### OBJECT: USE PROTOTYPE TO REDEFINE OBJECT FUNTION ####');
function User(nom, message) {
  this.nom = nom.toString();
  this.message = message.toString();
}
User.prototype.getNom = function () {
  return this.nom;
};
User.prototype.getMessage = function () {
  return this.message;
};
var o = new User('gege', 'HEllo');
console.log(o.getMessage() + ' ' + o.getNom());
// On redefinit le prototypage de User en utilisant la notation JSON
console.log('\n #### OBJECT: USE PROTOTYPE TO REDEFINE OBJECT FUNTION WITH JSON ANNOTATION ####');
User.prototype = {
  getNom: function () {
    return this.nom;
  },
  getMessage: function () {
    return this.message;
  },
  sayHello: function () {
    return 'Salut';
  }
}
var o = new User('Gianni', 'bienvenue!');
console.log(o.getMessage() + ' ' + o.getNom() + ' ' + o.sayHello());
