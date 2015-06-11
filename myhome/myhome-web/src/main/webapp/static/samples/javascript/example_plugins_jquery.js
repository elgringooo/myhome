(function($)
{ 
	$.fn.hoverFade=function(options)
	{
	   //options est donc un objet littéral, ne l'oublions pas !
           
           //On définit nos paramètres par défaut
           var defauts=
           {
               "vitesseFadeOut": "slow",
               "vitesseFadeIn": "fast"
           };  
           
           //On fusionne nos deux objets ! =D
           var parametres=$.extend(defauts, options); 
           
           return this.each(function()
	   {
		//On accèdera à la vitesse du fadeOut via parametres.vitesseFadeOut
                //et à celle du fadeIn via parametres.vitesseFadeIn
              
                //On veut que l'élément change au passage de la souris, on utilise donc mouseover() !
                $(this).mouseover(function()
                {
                    //On diminue donc l'opacité lentement
                    $(this).fadeOut(parametres.vitesseFadeOut,function()
                    {
                       //Une fois l'élément invisible, on le fait réapparaître rapidement !
                       $(this).fadeIn(parametres.vitesseFadeIn);
                    });
                });
	   });						   
	};
})(jQuery);