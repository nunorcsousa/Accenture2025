class User {
    constructor(name) {
        this.name = name;
        this.id = new Date().getTime();
        usersData.push(this);
    }

}

class Order {
    constructor() {
        this.id = undefined;
        this.userId = 0;
        this.products = [];
    }

    addProduct(product) {
        this.products.push(product);
    }

    createOrder() {
        this.id = new Date().getTime();
        // save in DB
        ordersData.push(this);
    }
}

class Product {
    constructor(name, extra) {
        this.name = name;
        this.extra = extra;
    }
}

const usersData = [];
const ordersData = [];
