function exportTo(type) {
    $('#table').tableExport({
        filename: 'table_%DD%-%MM%-%YY%',
        format: type,
        cols: '2,3,4'
    });
}

function exportAll(type) {
    $('#table').tableExport({
        filename: 'table_%DD%-%MM%-%YY%-month(%MM%)',
        format: type
    });
}

function filter() {
    var input, filter, table, tr, td, i;
    input = document.getElementById("search")
    filter = input.value.toUpperCase();
    table = document.getElementById("table");
    tr = table.getElementsByTagName("tr");
    for (i = 0; i < tr.length; i++) {
        td = tr[i].getElementsByTagName("td")[1];
        if (td) {
            if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
                tr[i].style.display = "";
            } else {
                tr[i].style.display = "none";
            }
        }
    }
}

$("#clear").on("click", function(el) {
     $("#table").find("tr:gt(0)").remove();
     $("#info").html("0 Info");
     $("#warn").html("0 Warn");
     $("#error").html("0 Error");
});

$('#bottom').on("click", function() {
    var window_height = $(window).height();
    var document_height = $(document).height();
    $('html,body').animate({
        scrollTop: window_height + document_height
    }, 'slow', function() {});
});

! function(e) {
    "use strict";
    e('.navbar-sidenav [data-toggle="tooltip"]').tooltip({
        template: '<div class="tooltip navbar-sidenav-tooltip" role="tooltip" style="pointer-events: none;"><div class="arrow"></div><div class="tooltip-inner"></div></div>'
    }), e("#sidenavToggler").click(function(o) {
        o.preventDefault(), e("body").toggleClass("sidenav-toggled"), e(".navbar-sidenav .nav-link-collapse").addClass("collapsed"), e(".navbar-sidenav .sidenav-second-level, .navbar-sidenav .sidenav-third-level").removeClass("show")
    }), e(".navbar-sidenav .nav-link-collapse").click(function(o) {
        o.preventDefault(), e("body").removeClass("sidenav-toggled")
    }), e("body.fixed-nav .navbar-sidenav, body.fixed-nav .sidenav-toggler, body.fixed-nav .navbar-collapse").on("mousewheel DOMMouseScroll", function(e) {
        var o = e.originalEvent,
            t = o.wheelDelta || -o.detail;
        this.scrollTop += 30 * (t < 0 ? 1 : -1), e.preventDefault()
    }), e(document).scroll(function() {
        e(this).scrollTop() > 100 ? e(".scroll-to-top").fadeIn() : e(".scroll-to-top").fadeOut()
    }), e('[data-toggle="tooltip"]').tooltip(), e(document).on("click", "a.scroll-to-top", function(o) {
        var t = e(this);
        e("html, body").stop().animate({
            scrollTop: e(t.attr("href")).offset().top
        }, 1e3, "easeInOutExpo"), o.preventDefault()
    })
}(jQuery);
