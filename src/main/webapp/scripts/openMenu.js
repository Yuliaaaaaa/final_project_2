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
function countSum(idx) {
    var price = this.document.getElementsByName("price").item(idx).value;
    var issues = this.document.getElementsByName("issues").item(idx).value;
    this.document.getElementsByName("disabledSum").item(idx).value = price*issues;
    this.document.getElementsByName("sum").item(idx).value = price*issues;
}