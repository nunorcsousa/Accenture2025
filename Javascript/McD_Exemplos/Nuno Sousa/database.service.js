function guardarNoStorage() {
  localStorage.setItem('pedidosAtivos', JSON.stringify(pedidos));
  localStorage.setItem('pedidosSatisfeitos', JSON.stringify(pedidosSatisfeitos));
  localStorage.setItem('contador', contador);
}

function carregarDoStorage() {
  const dadosAtivos = localStorage.getItem('pedidosAtivos');
  const dadosSatisfeitos = localStorage.getItem('pedidosSatisfeitos');
  const cont = localStorage.getItem('contador');

  if (dadosAtivos) {
    const json = JSON.parse(dadosAtivos);
    pedidos = json.map(p => new Pedido(p.id, p.cliente, p.produtos.map(pr => new ProdutoPedido(pr.produto, pr.subproduto, pr.extra))));
  }

  if (dadosSatisfeitos) {
    const json = JSON.parse(dadosSatisfeitos);
    pedidosSatisfeitos = json.map(p => new Pedido(p.id, p.cliente, p.produtos.map(pr => new ProdutoPedido(pr.produto, pr.subproduto, pr.extra))));
  }

  if (cont) contador = parseInt(cont, 10);
}