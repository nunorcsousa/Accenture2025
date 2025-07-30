/**
 * Iniciar uma tabela com o id do div parente +'-table' Ex: <div id='qwerty'><table id='qwerty-table'.
 * @param {string} divIdName Id do div onde se vai criar a tabela .
 * @param {array} colsNames nome das colunas da tabela .
 */
function initTable(divIdName, colsNames, needBtn) {
    let table = '<table id="' + divIdName + '-table" class="table"><thead><tr>'
    for (let index = 0; index < colsNames.length; index++) {
        table += '<th>' + colsNames[index] + '</th>';
    }
    if (needBtn) {
        table += '<th></th>';
    }
    table += '</tr></thead><tbody></tbody></table>';
    document.getElementById(divIdName).innerHTML = table;
}

/**
 * Remover uma tabela.
 * @param {string} divIdName Id do div onde foi criada a tabela .
 */
function removeTable(divIdName) {
    $('#' + divIdName).html("");
}

/**
 * Adicionar uma Row a tabela.
 * @param {string} divIdName Id do div onde esta a tabela .
 * @param {Object} objToAdd Objecto para adicionar .
 * @param {number} objToAdd.id Id do objecto .
 * @param {string} objToAdd.requesterName RequesterName do Objecto.
 * @param {array} objToAdd.products Products do Objecto .
 */
function addTableRow(divIdName, objToAdd, needBtn) {
    let rowToAdd = '<tr>'
    if (needBtn) {
        rowToAdd += `<td><button class="btn btn-danger" onclick="removeProductFromTempOrder('${objToAdd.name}','${objToAdd.extra}')">Del</button></td>`;
    }
    for (const key in objToAdd) {
        let valueOfKey = objToAdd[key];
        if (key === "products") {
            let productsToString = "";
            objToAdd[key].forEach(product => {productsToString += product.productToString() + "<br>";});
            valueOfKey = productsToString;
        }
        rowToAdd += '<td>' + valueOfKey + '</td>';
    }
    rowToAdd += '</tr>';
    document.querySelector(`#${divIdName}-table tbody`).insertAdjacentHTML('beforeend', rowToAdd)
}

/**
 * Atualizar dados da tabela.
 * @param {string} divIdName Id do div onde esta a tabela .
 * @param {array} data Dados da tabela .
 */
function updateTable(divIdName, data, needBtn) {
    document.querySelector(`#${divIdName}-table tbody`).innerHTML = '';
    data.forEach(dataRow => {
        addTableRow(divIdName, dataRow, needBtn)
    });
}