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
        td = tr[i].getElementsByTagName("td")[0];
        if (td) {
            if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
                tr[i].style.display = "";
            } else {
                tr[i].style.display = "none";
            }
        }
    }
}

