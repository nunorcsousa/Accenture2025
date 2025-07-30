class Order {
  constructor(requesterName) {
    this.id = undefined;
    this.requesterName = requesterName;
    this.products = [];
  }
  addProduct(name, extra) {
    this.products.push(new Product(name, extra));
  }
  save() {
    this.id = new Date().getTime();
    saveOrder(this)
  }
}

class Product {
  constructor(name, extra) {
    this.name = name;
    if (extra) {
      this.extra = extra
    }
  }
  /**
   * Converts a product to a string.
   * @returns {string}
   */
  productToString() {
    let output = '';
    for (const key in this) {
      output += (key === "extra") ? " Extra : " + this[key] : this[key]
    }
    return output;
  }
}