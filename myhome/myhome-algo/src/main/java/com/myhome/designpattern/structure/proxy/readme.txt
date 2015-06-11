Je me suis forme aux design patterns (les plus courants) sur ce site:
 
http://www.dofactory.com/Patterns/Patterns.aspx
 
Le proxy est ici (regarde le "structural code", c'est plus clair):
 
http://www.dofactory.com/Patterns/PatternProxy.aspx
 
Tu ne devrais avoir aucun mal a comprendre les exemples ; synthaxe C# et Java sont tres proches.
 
Pour synthetiser:
 
Tu as  
- un client
- un object  RealSubject qui fait le travail (ici, c'est afficher du text, mais ca peut etre un object situe sur une autre machine qui lance un travail de d'analyse de frequence par exemple.)
- un object Proxy qui pour chaque methodes appelle va appelle la methode du RealSubject.
 
RMI (Remote Method Invocation) et Corba fonctionnent avec ce pattern:
 
Tu manipules un proxy d'object local qui relait tes messages sur le server ou est reellement ton object distant.