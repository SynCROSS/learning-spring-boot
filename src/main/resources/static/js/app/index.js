const init = () => {
  try {
    document
      .getElementById('save-btn')
      .addEventListener('click', () => savePost());
    document
      .getElementById('update-btn')
      .addEventListener('click', () => updatePost());
  } catch (e) {
    console.error(e);
  }
};

const savePost = () => {
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

const updatePost = () => {
  const title = document.getElementById('title').value;
  const author = document.getElementById('author').value;

  const id = document.getElementById('id').value;

  axios
    .put('/api/v1/posts/' + id, { title, author })
    .then(() => {
      alert('Updating Post is Successful');
      location.href = '/';
    })
    .catch(window.alert);
};

init();