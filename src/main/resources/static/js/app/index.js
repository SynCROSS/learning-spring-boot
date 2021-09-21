
const savePost = () => {
  try {
    const title = document?.getElementById('title').value;
    const author = document?.getElementById('author').value;
    const content = document?.getElementById('content').value;

    axios
      .post('/api/v1/posts', { title, author, content })
      .then(() => {
        alert('Adding Post is Successful');
        window.location.href = '/';
      })
      .catch(window.alert);
  } catch (e) {
    console.error(e);
  }
};

const updatePost = () => {
  try {
    const title = document?.getElementById('title').value;
    const author = document?.getElementById('author').value;
    const id = document?.getElementById('id').value;

    axios
      .put('/api/v1/posts/' + id, { title, author })
      .then(() => {
        alert('Updating Post is Successful');
        window.location.href = '/';
      })
      .catch(window.alert);
  } catch (e) {
    console.error(e);
  }
};

const deletePost = () => {
  try {
    const id = document?.getElementById('id').value;

    axios
      .delete('/api/v1/posts/' + id)
      .then(() => {
        alert('Deleting Post is Successful');
        window.location.href = '/';
      })
      .catch(window?.alert);
  } catch (e) {
    console.error(e);
  }
};

const init = () => {
  try {
    const saveButton = document?.getElementById('save-btn');
    const updateButton = document?.getElementById('update-btn');
    const deleteButton = document?.getElementById('delete-btn');

    updateButton?.addEventListener('click', () => updatePost());
    saveButton?.addEventListener('click', () => savePost());
    deleteButton?.addEventListener('click', () => deletePost());
  } catch (e) {
    console.error(e);
  }
};

init();