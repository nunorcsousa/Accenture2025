class UserService {
  static getUsers() {
    return JSON.parse(localStorage.getItem("users") || "{}");
  }

  static saveUser(username, password) {
    const users = this.getUsers();
    if (users[username]) return false;
    users[username] = password;
    localStorage.setItem("users", JSON.stringify(users));
    return true;
  }

  static validateUser(username, password) {
    const users = this.getUsers();
    return users[username] === password;
  }

  static getCurrentUser() {
    return sessionStorage.getItem("currentUser");
  }

  static login(username) {
    sessionStorage.setItem("loggedIn", "true");
    sessionStorage.setItem("currentUser", username);
  }

  static logout() {
    sessionStorage.clear();
    location.reload();
  }
}

class FavoriteService {
  static getFavorites() {
    const user = UserService.getCurrentUser();
    return JSON.parse(localStorage.getItem(`favorites_${user}`) || "[]");
  }

  static saveFavorite(book) {
    const user = UserService.getCurrentUser();
    const key = `favorites_${user}`;
    const favs = this.getFavorites();
    if (!favs.find((b) => b.id === book.id)) {
      favs.push({ id: book.id, title: book.volumeInfo.title, link: book.volumeInfo.infoLink || "#" });
      localStorage.setItem(key, JSON.stringify(favs));
    }
  }

  static removeFavorite(id) {
    const user = UserService.getCurrentUser();
    let favs = this.getFavorites();
    favs = favs.filter((b) => b.id !== id);
    localStorage.setItem(`favorites_${user}`, JSON.stringify(favs));
  }
}
