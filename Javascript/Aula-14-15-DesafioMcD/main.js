initTable('pendingOrder', ['', 'Artigo', 'Extra'], true);
initTable('allOrders', ['Id', 'Nome do Cliente', 'Pedido']);
updateProductSelects();

let tempOrder = new Order();

function addSampleData() {
    data.push(...dataSample);
    updateOrders_Table_Counts();
}
addSampleData(); // Uncomment add sample data

document.getElementById('allOrdersFilter').addEventListener('change', e => updateOrdersTable());

document.getElementById('productType').addEventListener('change', e => updateProductSelects(document.getElementById('productType').value));

function updateProductSelects(productType) {
    switch (productType) {
        case "Acompanhamento":
            updateProductSelect(productNameAcompanhamentosList, []);
            break;
        case "Bebida":
            updateProductSelect(productNameBebidasList, productExtraBebidasList);
            break;
        default:
            updateProductSelect(productNameSanduichesList, productExtraSanduichesList);
            break;
    }
}

function updateProductSelect(nameList, extraList) {
    document.getElementById(`productName-input`).innerHTML = ''
    nameList.forEach((option) => {
        document.getElementById(`productName-input`).insertAdjacentHTML('beforeend', `<option value="${option}">${option}</option>`)
    });
    document.getElementById(`productExtra-input`).innerHTML = '';
    document.getElementById(`productExtra-input`).insertAdjacentHTML('beforeend', `<option value="">Normal</option>`)
    extraList.forEach((option) => {
        document.getElementById(`productExtra-input`).insertAdjacentHTML('beforeend', `<option value="${option}">${option}</option>`)
    });
}

function addProduct() {
    let productName = document.getElementById(`productName-input`).value;
    let productExtra = document.getElementById(`productExtra-input`).value;
    let tempProduct = {
        name: productName
    }
    if (productExtra != undefined && productExtra != "") {
        tempProduct.extra = productExtra;
    }
    if (productName != undefined && productName != "") {
        tempOrder.addProduct(productName, productExtra);
        addTableRow("pendingOrder", tempProduct, true);
        clearProductFields();
    }
}

function addOrder() {
    tempOrder.requesterName = document.getElementById(`requesterName-input`).value;
    if (tempOrder.requesterName != undefined && tempOrder.requesterName != "" && tempOrder.products.length > 0) {
        tempOrder.save();
        updateOrders_Table_Counts();
        clearOrderFields();
        document.getElementById(`errorMsg`).innerHTML = '';
    } else {
        if (tempOrder.requesterName == undefined || tempOrder.requesterName == "") {
            document.getElementById(`errorMsg`).innerHTML = 'Falta Nome do Cliente';
        }
        if (tempOrder.products.length == 0) {
            document.getElementById(`errorMsg`).innerHTML = 'Falta Produto';
        }
    }
}

function clearOrderFields() {
    clearProductFields();
    document.getElementById(`requesterName-input`).value = '';
    document.querySelector(`#pendingOrder-table tbody`).innerHTML = '';
    tempOrder = new Order();
}

function clearProductFields() {
    updateProductSelects(document.getElementById(`productType`).value);
}

function updateOrders_Table_Counts() {
    updateOrdersTable();
    updateOrderCounts();
}

function updateOrdersTable() {
    updateTable('allOrders', filterDataByProductExtra(document.getElementById(`allOrdersFilter`).value));
}

function updateOrderCounts() {
    let countExtra = countProductExtra();
    document.getElementById(`btn-all`).innerHTML = '<div class="row"><h4 class="col-10 col-md-12">Todos</h4><h4 class="col-2 col-md-12">' + data.length + '</h4></div>';
    document.getElementById(`btn-with`).innerHTML = '<div class="row"><h4 class="col-10 col-md-12">Com extra</h4><h4 class="col-2 col-md-12">' + countExtra + '</h4></div>';
    document.getElementById(`btn-without`).innerHTML = '<div class="row"><h4 class="col-10 col-md-12">Sem extra</h4><h4 class="col-2 col-md-12">' + (data.length - countExtra) + '</h4></div>';
}

function getOne(filter) {
    let filterData = orderDataById(filterDataByProductExtra(filter));
    if (filterData.length > 0) {
        const tempPrepareOrder = filterData[0];
        document.getElementById(`prepareOrder`).innerHTML = `
        <div class="h-100 d-flex flex-column">
            <div class="flex-grow-1">
                <h3 class="mt-3">Id do pedido : <span id="prepareOrderId">${tempPrepareOrder.id}</span></h3>
                <h3 id="prepareOrderRequesterName">Nome do Cliente : ${tempPrepareOrder.requesterName}</h3>
                <div id="prepareOrderProducts" style="height: 40vh;"></div>
            </div>
            <div class="text-center">
                <button id="btn-orderPrepared" type="submit" class="btn btn-primary col-12 py-5 mb-3" onclick="finishOrder()">Pedido preparado</button>
            </div>
        </div>`;
        initTable('prepareOrderProducts', ['Artigo', 'Extra']);
        updateTable('prepareOrderProducts', filterData[0].products);
    }
}

function finishOrder() {
    deleteOrderById(parseInt(document.getElementById(`prepareOrderId`).innerText));
    updateOrders_Table_Counts();
    document.getElementById(`prepareOrder`).innerHTML = '';
}

function removeProductFromTempOrder(name, extra) {
    let index = tempOrder.products.findIndex((product) => (extra == "undefined" && product.extra == undefined) ? product.name === name : product.name === name && product.extra === extra)
    if (index >= 0) {
        tempOrder.products.splice(index, 1);
    }
    updateTable("pendingOrder", tempOrder.products, true);
}
