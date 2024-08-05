// Bài 3:
const API_URL = {
    posts: 'https://jsonplaceholder.typicode.com/posts',
    albums: 'https://jsonplaceholder.typicode.com/albums',
    photos: 'https://jsonplaceholder.typicode.com/photos'
};

const dataList = document.getElementById('data-list');
const titleType = document.getElementById('title');
const buttons = {
    posts: document.getElementById('btn-posts'),
    albums: document.getElementById('btn-albums'),
    photos: document.getElementById('btn-photos')
};

const renderData = async (type) => {
    try {
        const response = await fetch(API_URL[type]);
        const data = await response.json();

        dataList.innerHTML = '';

        data.forEach(item => {
            const listItem = document.createElement('li');
            listItem.textContent = item.title;
            dataList.appendChild(listItem);
        });

        const typeText = type.charAt(0).toLocaleUpperCase() + type.slice(1);
        titleType.textContent = `Type: ${typeText}`;

        Object.keys(buttons).forEach(key => {
            buttons[key].classList.toggle('active', key === type);
        });
    } catch (error) {
        console.error('Error fetching data:', error);
    }
};

// Sự kiện click
buttons.posts.addEventListener('click', () => renderData('posts'));
buttons.albums.addEventListener('click', () => renderData('albums'));
buttons.photos.addEventListener('click', () => renderData('photos'));

renderData('posts');
