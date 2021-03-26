
(function ( $ ) {
    $.fn.orderBy = function(attribut) {
    console.log(attribut);
        $('#orderForm input[name=orderByAttribute]').attr('value',attribut);
        $('#orderForm').submit();
    };
}( jQuery ));