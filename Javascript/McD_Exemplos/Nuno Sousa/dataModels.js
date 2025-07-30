const cardapio = {
  "Hambúrguer": {
    "Big Mac": ["Sem cebola", "Molho especial"],
    "Quarterão com queijo": ["Sem queijo", "Molho barbecue"],
    "McRoyal": []
  },
  "Sanduíche": {
    "McChicken": ["Molho picante"],
    "Filet-o-Fish": []
  },
  "Bebida": {
    "Coca-Cola": [],
    "Sumo de Laranja": [],
    "Água": []
  },
  "Batatas": {
    "Batatas Pequenas": ["Molho ketchup"],
    "Batatas Grandes": ["Molho ketchup", "Molho barbecue"]
  },
  "Sobremesa": {
    "McFlurry": ["Topping Oreo", "Topping M&M's"],
    "Cheesecake": []
  }
};

class ProdutoPedido {
  constructor(produto, subproduto, extra) {
    this.produto = produto;
    this.subproduto = subproduto;
    this.extra = extra;
  }
  temExtra() {
    return this.extra && this.extra.trim() !== '';
  }
  toString() {
    return `${this.produto} - ${this.subproduto}${this.temExtra() ? ' (Extra: ' + this.extra + ')' : ''}`;
  }
}

class Pedido {
  constructor(id, cliente, produtos) {
    this.id = id;
    this.cliente = cliente;
    this.produtos = produtos;
  }
  toHTML() {
    return this.produtos.map(p => `<li>${p.toString()}</li>`).join('');
  }
  temAlgumExtra() {
    return this.produtos.some(p => p.temExtra());
  }
}

let pedidos = [];
let pedidosSatisfeitos = [];
let produtosTemp = [];
let contador = 1;

function atualizarSelectProdutos() {
  const produtoSel = document.getElementById('produto');
  produtoSel.innerHTML = `<option value="">Produto...</option>`;
  for (const produto in cardapio) {
    const opt = document.createElement("option");
    opt.text = produto;
    opt.value = produto;
    produtoSel.add(opt);
  }
}

function atualizarSubprodutos() {
  const produto = document.getElementById('produto').value;
  const subprodutoSel = document.getElementById('subproduto');
  subprodutoSel.innerHTML = `<option value="">Subproduto...</option>`;
  if (produto && cardapio[produto]) {
    Object.keys(cardapio[produto]).forEach(sub => {
      const opt = document.createElement("option");
      opt.text = sub;
      opt.value = sub;
      subprodutoSel.add(opt);
    });
  }
  document.getElementById('extraContainer').style.display = 'none';
}

function atualizarExtras() {
  const produto = document.getElementById('produto').value;
  const sub = document.getElementById('subproduto').value;
  const extraSel = document.getElementById('extra');
  const extras = cardapio[produto]?.[sub] || [];

  if (extras.length > 0) {
    document.getElementById('extraContainer').style.display = 'block';
    extraSel.innerHTML = `<option value="">Extra...</option>`;
    extras.forEach(extra => {
      const opt = document.createElement("option");
      opt.text = extra;
      opt.value = extra;
      extraSel.add(opt);
    });
  } else {
    document.getElementById('extraContainer').style.display = 'none';
  }
}

function removerPedido(id) {
  const index = pedidos.findIndex(p => p.id === id);
  if (index !== -1) {
    if (confirm("Tens a certeza que queres remover este pedido?")) {
      pedidos.splice(index, 1);
      atualizarTabela();
      guardarNoStorage();
    }
  }
}

function adicionarProdutoAoPedido() {
  const produto = document.getElementById('produto').value;
  const sub = document.getElementById('subproduto').value;
  const extra = document.getElementById('extra').value;

  if (!produto || !sub) return alert("Preenche produto e subproduto.");

  produtosTemp.push(new ProdutoPedido(produto, sub, extra));
  atualizarListaTemp();

  document.getElementById('extra').value = '';
  document.getElementById('subproduto').value = '';
}

function atualizarListaTemp() {
  const lista = produtosTemp.map(p => `<li>${p.toString()}</li>`).join('');
  document.getElementById('listaProdutosTemp').innerHTML = `<ul>${lista}</ul>`;
}

function criarPedido() {
  const cliente = document.getElementById('nomeCliente').value;
  if (!cliente || produtosTemp.length === 0) return alert("Preenche nome e produtos.");

  const novoPedido = new Pedido(contador++, cliente, produtosTemp);
  pedidos.push(novoPedido);
  produtosTemp = [];

  document.getElementById('nomeCliente').value = '';
  document.getElementById('listaProdutosTemp').innerHTML = '';

  atualizarTabela();
  atualizarSatisfeitos();
  guardarNoStorage();
}

function marcarPreparado(id) {
  const index = pedidos.findIndex(p => p.id === id);
  if (index !== -1) {
    pedidosSatisfeitos.push(pedidos[index]);
    pedidos.splice(index, 1);
    atualizarTabela();
    atualizarSatisfeitos();
    guardarNoStorage();
  }
}

function atualizarTabela() {
  const filtro = document.getElementById('filtroTipo').value;
  const tbody = document.getElementById('tabelaPedidos');
  tbody.innerHTML = '';

  const pedidosFiltrados = pedidos.filter(p => {
    if (filtro === 'comExtras') return p.temAlgumExtra();
    if (filtro === 'semExtras') return !p.temAlgumExtra();
    return true;
  });

  pedidosFiltrados.forEach(p => {
    const row = `<tr>
      <td>${p.id}</td>
      <td>${p.cliente}</td>
      <td><ul>${p.toHTML()}</ul></td>
      <td>
        <div class="d-flex gap-2">
          <button class="btn btn-success btn-sm" onclick="marcarPreparado(${p.id})">Pedido preparado</button>
          <button class="btn btn-danger btn-sm" onclick="removerPedido(${p.id})">Remover</button>
        </div>
      </td>
    </tr>`;
    tbody.innerHTML += row;
  });
}

function atualizarSatisfeitos() {
  const ul = document.getElementById('pedidosSatisfeitos');
  ul.innerHTML = pedidosSatisfeitos.map(p =>
    `<li class="list-group-item">${p.cliente}: <ul>${p.toHTML()}</ul></li>`
  ).join('');
}

window.onload = function() {
  carregarDoStorage();
  atualizarSelectProdutos();
  atualizarTabela();
  atualizarSatisfeitos();
};