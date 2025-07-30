function helperArrayToHTMLElements(a, tagName) {
  let elements = '';
  for (let index = 0; index < a.length; index++) {
    const element = a[index];
    elements += `<${tagName}>${element}</${tagName}>`
  }
  return elements;
}
class MyTable {
  constructor(tableId){
    this.tableId = tableId;
  }

  initTable(hnames) {
    let headElements = helperArrayToHTMLElements(hnames, 'th');
    document.getElementById(this.tableId).innerHTML = `
  <table class="table table-hover">
    <thead>
      <tr>
        ${headElements}
      </tr>
    </thead>
    <tbody id="${this.tableId}_body">
    </tbody>
  </table>`
  }

  /** 
   * @param {string} tableBodyId test tableBodyId
   * @param {array} a teste a
  */
  addArrayToTableRow(a) {
    const tableBodyId = this.tableId + "_body";
    document.getElementById(tableBodyId).insertAdjacentHTML('beforeend', `
      <tr>
        ${helperArrayToHTMLElements(a, 'td')}
      </tr>`)

  }

  cleanTableBody(){
    const tableBodyId = this.tableId + "_body";
    document.getElementById(tableBodyId).innerHTML = "";
  }
}