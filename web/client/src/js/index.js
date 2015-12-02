$(function () {
  // global dependencies
  var deps = ["startup"];

  define("main", deps, function () {
    
  });

  var unslider = $('.banner').unslider();

  $('.unslider-arrow').click(function () {
    var fn = this.className.split(' ')[1];

    //  Either do unslider.data('unslider').next() or .prev() depending on the className
    unslider.data('unslider')[fn]();
  });
});
