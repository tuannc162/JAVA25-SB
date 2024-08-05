const btn = document.getElementById('btn');
const image = document.getElementById('image');
const select = document.getElementById('breed-list');

btn.addEventListener("click", function () {
    getRandomImage()
});

async function getRandomImage() {
    const breed = select.value;
        try {
            let res = await axios.get(`https://dog.ceo/api/breed/${breed}/images/random`);
            image.src = res.data.message;
        } catch (error) {
            console.log(error);
        };
};

async function getBreedList() {
    try {
        let res = await axios.get("https://dog.ceo/api/breeds/list/all");
        renderBreed(res.data.message);
    } catch (error) {
        console.log(error);
    }
};

function renderBreed(breeds) {
    let options = [];

    for (let breed in breeds) {
        if (breeds[breed].length > 0) {
            breeds[breed].forEach(subBreed => {
                options.push(`<option value="${breed}/${subBreed}">${breed} - ${subBreed}</option>`);
            });
        } else {
            options.push(`<option value="${breed}">${breed}</option>`);
        }
    }
    select.innerHTML = options.join('');
};

getBreedList();