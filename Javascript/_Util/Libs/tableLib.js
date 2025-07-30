function helperArrayToHTMLElements(a, tagName) {
  let elements = '';
  for (let index = 0; index < a.length; index++) {
    const element = a[index];
    elements += `<${tagName}>${element}</${tagName}>`
  }
  return elements;
}
class MyTable {
  initTable(tableId, hnames) {
    let headElements = helperArrayToHTMLElements(hnames, 'th');
    document.getElementById(tableId).innerHTML = `
  <table class="table">
    <thead>
      <tr>
        <th scope="col">#</th>
        ${headElements}
      </tr>
    </thead>
    <tbody id="${tableId}_body">
    </tbody>
  </table>`
  }

  /**
   * @param {string} tableBodyId test tableBodyId
   * @param {array} a teste a
  */
  addArrayToTableRow(tableBodyId, a, index = -1) {
    document.getElementById(tableBodyId).insertAdjacentHTML('beforeend', `
      <tr>
        <th scope="row">${(index >= 0)? index : document.getElementById(tableBodyId).rows.length + 1}</th>
        ${helperArrayToHTMLElements(a, 'td')}
      </tr>`)

  }
}