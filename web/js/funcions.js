
$(document).ready(function() {
    Funcions.reescriureReveal();
});

//Cream un namespace per les funcions genèriques
var Funcions = {
    
    // <editor-fold defaultstate="collapsed" desc="Funció que recol·loca el listener als disparadors de reveal.">
    /**
     * Reescribim la funció que col·loca el listener als disparadors de reveal
     * @returns {void}
     */
    reescriureReveal: function() {
        $('a[data-reveal-id]').on('click', function(e) {
            e.preventDefault();
            var modalLocation = $(this).attr('data-reveal-id');
            $('#'+modalLocation).reveal($(this).data());
        });
    },
    // </editor-fold>  
    
    // <editor-fold defaultstate="collapsed" desc="Funció que mostra el popup d'error.">
    
    /**
     * Mostra el popup que indica que s'ha produit un error
     * @returns {void}
     */
    revealError: function() {
        $("#errorModal").reveal();
    },
    // </editor-fold>  
    
    // <editor-fold defaultstate="collapsed" desc="Funció que lleva els missatges d'error.">
    
    /**
     * Lleva tots els missatges d'error
     * @return {void} 
     */
    llevarErrors: function() {
        $(".errorText").remove();
    },
    // </editor-fold>  
    
    // <editor-fold defaultstate="collapsed" desc="Funció que col·loca els missatges d'error.">
    
    /**
     * Posa un missatge d'error abans de l'element donat
     * @param {HTML element} elem L'element on mostrar l'error
     * @param {String} text El text d'error
     * @return {void}
     */
    mostrarError: function(elem, text) {
        var errElem = '<span class="errorText">' + text + '</span>';
        $(elem).before(errElem);
        $(elem).focus();
    },
    // </editor-fold>  
    
    // <editor-fold defaultstate="collapsed" desc="Funció que realitza una petició AJAX.">
    /**
     * Realitza una petició AJAX
     * @param {String} url URL a la que realitzar la consulta (ha d'incloure els paràmetres)
     * @param {String} success Funció a la que cridar en cas de funcionament normal
     * @param {String} error Funció a la que cridar en cas d'error
     * @returns {void}
     */
    peticioAjax: function(url, success, error) {
        var req = prepararRequest();
        req.open("POST", url, true);
        req.onreadystatechange = ajaxCallback;
        req.send(null);
        
        /**
         * Preparar un objecte per realitzar una petició AJAX
         * @returns {XMLHttpRequest|ActiveXObject}
         */
        function prepararRequest() {
            if (window.XMLHttpRequest) {
                if (navigator.userAgent.indexOf('MSIE') != -1) {
                    isIE = true;
                }
                return new XMLHttpRequest();
            } else if (window.ActiveXObject) {
                isIE = true;
                return new ActiveXObject("Microsoft.XMLHTTP");
            }
        }
        
        /**
         * Callback per la resposta HTTP
         */
        function ajaxCallback() {
            if (req.readyState == 4) { //Esperam a l'estat 4 (Petició acabada i resposta llesta)
                if (req.status == 200) success.call(this, req.responseXML);
                else error.call(this);
            }
        }
    }
    // </editor-fold>  
};