const createLocalStorage = (name, value) => {
  localStorage.setItem(name, value);
}

const getLocalStorage = (name) => {
  return localStorage.getItem(name);
}
