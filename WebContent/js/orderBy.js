
(function ( $ ) {
    $.fn.orderBy = function(attribut) {
        $('#orderForm input[name=orderByAttribute]').attr('value',attribut);
        $('#orderForm').submit();
    };
}( jQuery ));