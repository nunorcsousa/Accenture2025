const db = [];

// add order
const adOrder = (order) => {
    db.push(order);
}

// remove order by id
const removeOrder = (id) => {
    const index = db.findIndex(order => order.id === id);
    if (index !== -1) {
        db.splice(index, 1);
    }
}

