// script.js

const form = document.querySelector('#search-form');
const searchInput = document.querySelector('#search-input');
const resultsList = document.querySelector('#search-results');
const resultsSection = document.querySelector('#results-section');


    // Add event listener to the search form
    searchForm.addEventListener('submit', (e) => {
      // Prevent the default behavior of form submission
      e.preventDefault();
      
      // Get the search term entered by the user
      const searchTerm = searchInput.value.trim();
    
      // If search term is not empty, perform search
      if (searchTerm !== '') {
        // Clear the search input field
        searchInput.value = '';
        
        // Filter the books array based on the search term
        const filteredBooks = books.filter(book => book.title.includes(searchTerm) || book.author.includes(searchTerm));
        
        // If no books match the search term, display a message
        if (filteredBooks.length === 0) {
          results.innerHTML = `<p>No results found for "${searchTerm}". Please try again.</p>`;
        }
        // If there are matching books, display them
        else {
          // Create a new array of HTML strings for each book
          const bookHTML = filteredBooks.map(book => {
            return `
              <div class="book">
                <h2 class="book-title">${book.title}</h2>
                <p class="book-author">By ${book.author}</p>
                <p class="book-description">${book.description}</p>
                <p class="book-genre">${book.genre}</p>
              </div>
            `;
          });
          
          // Join the array of book HTML strings into a single string
          const allBooksHTML = bookHTML.join('');
          
          // Display the books on the page
          results.innerHTML = allBooksHTML;
        }
      }
    });
    