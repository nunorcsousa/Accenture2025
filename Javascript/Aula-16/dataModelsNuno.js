
class UI {
  static renderFavorites() {
    const favs = FavoriteService.getFavorites();
    const favList = document.getElementById("favorites");
    favList.innerHTML = "";
    favs.forEach((book) => {
      favList.appendChild(this.createFavoriteListItem(book));
    });
  }

  static createFavoriteListItem(book) {
    const li = document.createElement("li");
    li.className = "list-group-item d-flex justify-content-between align-items-center";
    li.dataset.bookId = book.id;
    li.innerHTML = `
      <a href="${book.link}" target="_blank" class="text-decoration-none text-primary fw-bold flex-grow-1">
        ${book.title}
      </a>
      <button class="btn btn-sm btn-outline-danger ms-2">
        <i class="fas fa-trash"></i>
      </button>
    `;
    li.querySelector("button").addEventListener("click", () => {
      FavoriteService.removeFavorite(book.id);
      li.remove();
    });
    return li;
  }

  static renderCard(book) {
    const stack = document.getElementById("cardStack");
    stack.innerHTML = "";
    if (!book) {
      stack.innerHTML = '<div class="text-center text-light mt-5">Sem mais resultados.</div>';
      return;
    }

    const card = document.createElement("div");
    card.className = "book-card shadow-lg";
    const title = book.volumeInfo.title || "Sem título";
    const authors = book.volumeInfo.authors?.join(", ") || "Desconhecido";
    const infoLink = book.volumeInfo.infoLink || "#";
    const description = book.volumeInfo.description?.substring(0, 200) + "..." || "Sem descrição.";
    const thumbnail = book.volumeInfo.imageLinks?.thumbnail || "";

    card.innerHTML = `
      <div class="card h-100 text-dark border-0">
        <a href="${infoLink}" target="_blank">
          <img src="${thumbnail}" class="card-img-top img-fluid rounded-top" style="object-fit: contain; height: 200px;" alt="Capa do livro">
        </a>
        <div class="card-body d-flex flex-column">
          <h5 class="card-title text-center fw-bold">${title}</h5>
          <p class="card-subtitle text-muted text-center mb-2">${authors}</p>
          <p class="card-text small flex-grow-1">${description}</p>
          <a href="${infoLink}" target="_blank" class="btn btn-outline-primary mt-2 w-100">Ver mais</a>
        </div>
      </div>
    `;

    stack.appendChild(card);
  }

  static updateUserName() {
    document.getElementById('currentUserName').innerText = UserService.getCurrentUser();
  }

  static loadCategories(categorias) {
    const select = document.getElementById("categorySelect");
    select.innerHTML = `<option value="">Categories...</option>`;
    categorias.forEach(c => {
      const opt = document.createElement("option");
      opt.value = c;
      opt.textContent = c;
      select.appendChild(opt);
    });
  }
}

class BookApp {
  constructor() {
    this.books = [];
    this.currentIndex = 0;
    this.categorias = ["Fiction", "Science", "History", "Poetry", "Art", "Biography", "Technology", "Self-Help"];
  }

  async searchBooks(query) {
    const res = await fetch(`https://www.googleapis.com/books/v1/volumes?q=${encodeURIComponent(query)}&maxResults=40`);
    if (res.ok) {
      const data = await res.json();
      this.books = data.items || [];
      this.currentIndex = 0;
      UI.renderCard(this.books[this.currentIndex]);
    }
  }

  async searchByCategory(category) {
    const res = await fetch(`https://www.googleapis.com/books/v1/volumes?q=subject:${category}&maxResults=40`);
    if (res.ok) {
      const data = await res.json();
      this.books = data.items || [];
      this.currentIndex = 0;
      UI.renderCard(this.books[this.currentIndex]);
    }
  }

  nextBook() {
    this.currentIndex++;
    if (this.currentIndex < this.books.length) {
      UI.renderCard(this.books[this.currentIndex]);
    } else {
      UI.renderCard(null);
    }
  }

  saveCurrentFavorite() {
    const book = this.books[this.currentIndex];
    if (!book) return;
    FavoriteService.saveFavorite(book);
    UI.renderFavorites();
    this.nextBook();
  }

  rejectBook() {
    this.nextBook();
  }
}

// Instanciar app e configurar eventos
document.addEventListener("DOMContentLoaded", () => {
  const app = new BookApp();
  UI.loadCategories(app.categorias);
  UI.updateUserName();

  if (sessionStorage.getItem("loggedIn") === "true") {
    document.getElementById("loginSection").style.display = "none";
    document.getElementById("appContainer").style.display = "flex";
    UI.renderFavorites();
  }

  document.getElementById("searchInput").addEventListener("focus", () => {
    document.getElementById("categorySelect").selectedIndex = 0;
  });

  document.querySelector("button[onclick='searchBooks()']").addEventListener("click", () => {
    const query = document.getElementById("searchInput").value.trim();
    if (query) zapp.searchBooks(query);
  });

  document.getElementById("categorySelect").addEventListener("change", (e) => {
    if (e.target.value) app.searchByCategory(e.target.value);
  });

  document.querySelector("button[onclick='saveFavorite()']").addEventListener("click", () => {
    app.saveCurrentFavorite();
  });

  document.querySelector("button[onclick='rejectBook()']").addEventListener("click", () => {
    app.rejectBook();
  });

  document.querySelector("button[onclick='logout()']").addEventListener("click", () => {
    UserService.logout();
  });

  window.login = () => {
    const user = document.getElementById("username").value;
    const pass = document.getElementById("password").value;
    if (UserService.validateUser(user, pass)) {
      UserService.login(user);
      document.getElementById("loginSection").style.display = "none";
      document.getElementById("appContainer").style.display = "flex";
      UI.updateUserName();
      UI.renderFavorites();
    } else {
      alert("Credenciais inválidas.");
    }
  };

  window.register = () => {
    const user = document.getElementById("username").value;
    const pass = document.getElementById("password").value;
    if (!user || !pass) return alert("Preencha todos os campos.");
    if (UserService.saveUser(user, pass)) {
      alert("Registo feito com sucesso!");
    } else {
      alert("Utilizador já existe.");
    }
  };
});
