
  document.getElementById('login-form').addEventListener('submit', function(event) {
    event.preventDefault();
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;
  
    // Construct the request body
    const requestBody = {
      username: username,
      password: password
    };
  
    // Make the POST request to the login API
    fetch('http://localhost:8080/api/geekconnect/users/login', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(requestBody)
    })
    .then(response => {
      if (!response.ok) {
        throw new Error('Network response was not ok');
      }
      return response.json();
    })
    .then(data => {
      // Check the response status
      if (data.status === 'OK') {
        // Login successful, redirect to dashboard
        // window.userId = data.id;
        localStorage.setItem('userId', data.id);
        window.location.href = 'dashboard.html';
      } else {
        // Login failed, display error message
        alert(data.message);
      }
    })
    .catch(error => {
      console.error('Error:', error);
      // Display generic error message
      alert('An error occurred during login');
    });
  });
  
  function loadPage(page) {
    switch (page) {
      case 'profile':
        // Load profile page
        fetchProfile();
        break;
      case 'assignment':
        // Load assignments page
        fetchAssignments();
        break;
      case 'courses':
        // Load courses page
        fetchCourses();
        break;
      default:
        console.error('Invalid page:', page);
    }
  }
  
  function fetchAssignments() {
    // Get the user ID from somewhere (e.g., local storage, session)
    const userId = localStorage.getItem('userId'); 
  
    // Make the request to fetch assignments for the user
    fetch(`http://localhost:8080/api/geekconnect/${userId}/assignment/`, {
      headers: {
        'Content-Type': 'application/json'
      }
    })
    .then(response => {
      if (!response.ok) {
        throw new Error('Network response was not ok');
      }
      return response.json();
    })
    .then(data => {
      // Check the response status
      if (data.status === 'OK') {
        // Assignments fetched successfully, render them
        renderAssignments(data.object);
      } else {
        // Display error message
        alert(data.message);
      }
    })
    .catch(error => {
      console.error('Error:', error);
      // Display generic error message
      alert('An error occurred while fetching assignments');
    });
  }
  
  function renderAssignments(assignments) {
    let contentDiv = document.getElementById('content');
    contentDiv.innerHTML = ''; // Clear previous content
    
    if (assignments.length === 0) {
      // If no assignments found
      contentDiv.innerHTML = '<p>No assignments found.</p>';
    } else {
      // Render each assignment
      assignments.forEach(assignment => {
        let assignmentDiv = document.createElement('div');
        assignmentDiv.innerHTML = `
          <h2>${assignment.assignmentName}</h2>
          <p>Description: ${assignment.description}</p>
          <p>Deadline: ${assignment.deadline}</p>
          <p>Course: ${assignment.course.courseName}</p>
          <p>Professor: ${assignment.course.professor.fullName}</p>
        `;
        contentDiv.appendChild(assignmentDiv);
      });
    }
  }

  function fetchProfile() {
    // Get the user ID from local storage
    const userId = localStorage.getItem('userId');
  
    // Make the request to fetch the user's profile data
    fetch(`http://localhost:8080/api/geekconnect/users/id?id=${userId}`, {
      headers: {
        'Content-Type': 'application/json'
      }
    })
    .then(response => {
      if (!response.ok) {
        throw new Error('Network response was not ok');
      }
      return response.json();
    })
    .then(data => {
      // Check the response status
      if (data.status === 'OK') {
        // Profile data fetched successfully, render it
        renderProfile(data.object);
      } else {
        // Display error message
        alert(data.message);
      }
    })
    .catch(error => {
      console.error('Error:', error);
      // Display generic error message
      alert('An error occurred while fetching profile data');
    });
  }
  
  function renderProfile(profileData) {
    // Fetch the profile template file
    fetch('profile.html')
      .then(response => {
        if (!response.ok) {
          throw new Error('Failed to fetch profile template');
        }
        return response.text();
      })
      .then(html => {
        // Replace placeholders in the template with profile data
        html = html.replace('{{id}}', profileData.id)
                   .replace('{{username}}', profileData.username)
                   .replace('{{role}}', profileData.role)
                   .replace('{{email}}', profileData.email)
                   .replace('{{fullname}}', profileData.fullName);

        // Render the profile HTML content in the content area
        const contentDiv = document.getElementById('content');
        contentDiv.innerHTML = html;
      })
      .catch(error => {
        console.error('Error:', error);
        // Display generic error message
        alert('An error occurred while rendering profile');
      });
  }

//
//  function renderProfile(profileData) {
//    // Fetch the profile template file
//    fetch('profile.html')
//      .then(response => {
//        if (!response.ok) {
//          throw new Error('Failed to fetch profile template');
//        }
//        return response.text();
//      })
//      .then(html => {
//        // Replace placeholders in the template with profile data
//        html = html.replace('%id%', profileData.id)
//                   .replace('%username%', profileData.username)
//                   .replace('%role%', profileData.role)
//                   .replace('%email%', profileData.email)
//                   .replace('%fullname%', profileData.fullName);
//
//        // Render the profile HTML content in the content area
//        const contentDiv = document.getElementById('content');
//        contentDiv.innerHTML = html;
//      })
//      .catch(error => {
//        console.error('Error:', error);
//        // Display generic error message
//        alert('An error occurred while rendering profile');
//      });
//  }

  
  function logout() {
    // Perform logout actions, e.g., clearing session, redirecting to login page
    alert('Logged out successfully');
    // Redirect to login page
    window.location.href = 'index.html';
  }
  