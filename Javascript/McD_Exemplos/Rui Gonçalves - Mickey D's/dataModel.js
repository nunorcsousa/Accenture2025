class Order {
  constructor(name) {
    this.id = new Date().getTime();
    this.requesterName = name
    this.products = [];
    this.ready = false;
  }

  addProduct(productName, productExtra) {
    const product = new Product(productName, (productExtra != "Regula Shmegula") ? productExtra : "");
    this.products.push(product);
    return product;
  }

  save() {
    // TODO: save in db 
  }
}

class Product {
  constructor(name, extra) {
    this.id = new Date().getTime();
    this.name = name;
    this.extra = extra;
  }

  // add more methods
}