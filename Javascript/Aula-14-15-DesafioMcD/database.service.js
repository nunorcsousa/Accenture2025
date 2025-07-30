const data = [];
/**
 * Adicionar Pedido'.
 * @param {Object} order Pedido para adicionar.
 * @param {number} order.id .
 * @param {string} order.requestername .
 * @param {Object} order.products .
 */
function saveOrder(order) {
  data.push(order);
}

/**
 * Remover Pedido por ID'.
 * @param {number} idNumber Id do pedido.
 */
function deleteOrderById(idNumber) {
  data.splice(data.findIndex(order => order.id === idNumber), 1);
}

/**
 * Order Data por id.
 * @param {array} data .
 * @return {array} return data por ordem de id.
 */
function orderDataById(fullData) {
  return fullData.sort((leftSide, rigthSide) => leftSide.id - rigthSide.id);
}

/**
 * Filtrar os dados por product.extra .
 * @param {string} Filter name.
 * @returns {array}
 */
function filterDataByProductExtra(filter) {
  if (filter === "all") return data;
  return data.filter(order => (orderWithProductExtra(order) && filter === "withExtra" || !orderWithProductExtra(order) && filter === "withoutExtra"));
}

/**
 * Count Orders with product.extra .
 * @returns {number}
 */
function countProductExtra() {
  return data.filter(order => orderWithProductExtra(order)).length;
}

/**
 * Order with product.extra .
 * @param {object} Order to check.
 * @returns {boolean}
 */
function orderWithProductExtra(order) {
  return order.products.some(product => product.hasOwnProperty("extra"));
}