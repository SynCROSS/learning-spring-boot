const init = () => {
  try {
    document
      .getElementById('save-btn')
      .addEventListener('click', () => savePosts());
  } catch (e) {
    console.error(e);
  }
};

const savePosts = () => {
  const title = document.getElementById('title').value;
  const author = document.getElementById('author').value;
  const content = document.getElementById('content').value;

  axios
    .post('/api/v1/posts', { title, author, content })
    .then(() => {
      alert('Adding Post is Successful');
      location.href = '/';
    })
    .catch(window.alert);
};

init();
