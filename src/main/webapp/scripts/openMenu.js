function openMenu(id) {
    var details = this.document.getElementsByName("details");
    if (details.item(id).style.display == 'none'){
        var inputList = Array.prototype.slice.call(details);
        inputList.forEach(closeItem);
        details.item(id).style.display = 'inline-block';
    }
    else
        details.item(id).style.display = 'none';
}
function closeItem(value, index, ar) {
    document.getElementsByName('details').item(index).style.display = 'none';
}