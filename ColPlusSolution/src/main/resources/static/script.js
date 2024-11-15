// LOGIN - MAIN.JS - dp 2017

$(document).ready(function() {

  // LOGIN TABS
  var tab = $('.tabs h3 a');
  tab.on('click', function(event) {
    event.preventDefault();
    tab.removeClass('active');
    $(this).addClass('active');
    var tab_content = $(this).attr('href');
    $('div[id$="tab-content"]').removeClass('active');
    $(tab_content).addClass('active');
  });

  // SLIDESHOW
  $('#slideshow > div:gt(0)').hide();
  setInterval(function() {
    $('#slideshow > div:first')
      .fadeOut(1000)
      .next()
      .fadeIn(1000)
      .end()
      .appendTo('#slideshow');
  }, 3850);

  // CUSTOM JQUERY FUNCTION FOR SWAPPING CLASSES
  $.fn.swapClass = function(remove, add) {
    this.removeClass(remove).addClass(add);
    return this;
  };

  // SHOW/HIDE PANEL ROUTINE
  $('.agree, .forgot, #toggle-terms, .log-in, .sign-up').on('click', function(event) {
    event.preventDefault();

    var terms = $('.terms'),
        recovery = $('.recovery'),
        close = $('#toggle-terms'),
        arrow = $('.tabs-content .fa');

    if ($(this).hasClass('agree') || $(this).hasClass('log-in') || ($(this).is('#toggle-terms') && terms.hasClass('open'))) {
      if (terms.hasClass('open')) {
        terms.swapClass('open', 'closed');
        close.swapClass('open', 'closed');
        arrow.swapClass('active', 'inactive');
      } else if (!$(this).hasClass('log-in')) {
        terms.swapClass('closed', 'open').scrollTop(0);
        close.swapClass('closed', 'open');
        arrow.swapClass('inactive', 'active');
      }
    } else if ($(this).hasClass('forgot') || $(this).hasClass('sign-up') || $(this).is('#toggle-terms')) {
      if (recovery.hasClass('open')) {
        recovery.swapClass('open', 'closed');
        close.swapClass('open', 'closed');
        arrow.swapClass('active', 'inactive');
      } else if (!$(this).hasClass('sign-up')) {
        recovery.swapClass('closed', 'open');
        close.swapClass('closed', 'open');
        arrow.swapClass('inactive', 'active');
      }
    }
  });

  // DISPLAY MESSAGE
  $('.recovery .button').on('click', function(event) {
    event.preventDefault();
    var message = $('.recovery .mssg');
    message.addClass('animate');
    setTimeout(function() {
      $('.recovery').swapClass('open', 'closed');
      $('#toggle-terms').swapClass('open', 'closed');
      $('.tabs-content .fa').swapClass('active', 'inactive');
      message.removeClass('animate');
    }, 2500);
  });

});


//navbar 
$(document).ready(function(){
  $(window).scroll(function(){
    var scrollTop = $(window).scrollTop();

    // Fija la navbar en la parte superior después de cierto desplazamiento
    if (scrollTop > 49) {
        $('body').addClass('header-fixed');
    } else {
        $('body').removeClass('header-fixed');
    }

    // Cambia el estilo de la navbar al pasar el punto de transición
    var topOffset = $('#demosection2').offset().top;
    var headerHeight = $('#topnav').height();
    var transitionPoint = topOffset - headerHeight;
    
    if (scrollTop > transitionPoint) {
        $('#topnav').addClass('alt-header');
    } else {
        $('#topnav').removeClass('alt-header');
    }
  });
});
