
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
        localStorage.setItem('role', data.role);
        window.location.href = 'dashboard';
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


  // Function to highlight the active link
  function highlightActiveLink(pageName) {
    const links = document.querySelectorAll('.drawer a');
    links.forEach(link => {
      if (link.textContent.toLowerCase() === pageName) {
        link.classList.add('active');
      } else {
        link.classList.remove('active');
      }
    });
  }
  
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
    highlightActiveLink(page);
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
    // Create table element and its header
    let tableHTML = '<table>' +
                      '<thead>' +
                        '<tr>' +
                          '<th>Assignment Name</th>' +
                          '<th>Description</th>' +
                          '<th>Deadline</th>' +
                          '<th>Course</th>' +
                          '<th>Professor</th>' +
                        '</tr>' +
                      '</thead>' +
                      '<tbody>';

    // Append each assignment to the table body
    assignments.forEach(assignment => {
      // Fetch the assignment template file
      fetch('assignment.html')
        .then(response => {
          if (!response.ok) {
            throw new Error('Failed to fetch assignment template');
          }
          return response.text();
        })
        .then(html => {
          // Replace placeholders in the template with assignment data
          html = html.replace('{{assignmentName}}', assignment.assignmentName)
                     .replace('{{description}}', assignment.description)
                     .replace('{{deadline}}', assignment.deadline)
                     .replace('{{courseName}}', assignment.course.courseName)
                     .replace('{{professorName}}', assignment.course.professor.fullName);

          // Append the assignment row to the table body
          tableHTML += '<tr>' + html + '</tr>';

          // If this is the last assignment, close the table
          if (assignments.indexOf(assignment) === assignments.length - 1) {
            tableHTML += '</tbody></table>';
            contentDiv.innerHTML = tableHTML;

            // Check user role and add floating action button if role is professor
            const role = localStorage.getItem('role');
            if (role === 'PROFESSOR') {
              const floatingButton = document.createElement('div');
              floatingButton.classList.add('floating-button');
              floatingButton.innerHTML = '<button onclick="openAssignmentPopup()">Create Assignment</button>';
              contentDiv.appendChild(floatingButton);
            }
          }
        })
        .catch(error => {
          console.error('Error:', error);
          // Display generic error message
          alert('An error occurred while rendering assignments');
        });
    });
  }
}


//function renderAssignments(assignments) {
//  let contentDiv = document.getElementById('content');
//  contentDiv.innerHTML = ''; // Clear previous content
//
//  if (assignments.length === 0) {
//    // If no assignments found
//    contentDiv.innerHTML = '<p>No assignments found.</p>';
//  } else {
//    // Create table element and its header
//    let tableHTML = '<table>' +
//                      '<thead>' +
//                        '<tr>' +
//                          '<th>Assignment Name</th>' +
//                          '<th>Description</th>' +
//                          '<th>Deadline</th>' +
//                          '<th>Course</th>' +
//                          '<th>Professor</th>' +
//                        '</tr>' +
//                      '</thead>' +
//                      '<tbody>';
//
//    // Append each assignment to the table body
//    assignments.forEach(assignment => {
//      // Replace placeholders in the template with assignment data
//      let rowHTML = `
//        <tr>
//          <td>${assignment.assignmentName}</td>
//          <td>${assignment.description}</td>
//          <td>${assignment.deadline}</td>
//          <td>${assignment.course.courseName}</td>
//          <td>${assignment.course.professor.fullName}</td>
//        </tr>
//      `;
//
//      // Append the row HTML to the table
//      tableHTML += rowHTML;
//    });
//
//    // Close the table and set the content
//    tableHTML += '</tbody></table>';
//    contentDiv.innerHTML = tableHTML;
//
//    // Add or remove floating action button based on user role
//    const role = localStorage.getItem('role');
//    const floatingButton = document.getElementById('floatingButton');
//
//    if (role === 'professor') {
//      floatingButton.classList.remove('hidden');
//      // Add click event to the floating button
//      floatingButton.addEventListener('click', openAssignmentPopup);
//    } else {
//      floatingButton.classList.add('hidden');
//    }
//  }
//
//  // Function to open the assignment creation popup
//  function openAssignmentPopup() {
//    const assignmentModal = document.getElementById('assignmentModal');
//    assignmentModal.classList.remove('hidden');
//    // Populate the popup content dynamically as needed
//  }
//
//  // Create the "Create Assignment" button after rendering assignments
//  const createAssignmentButton = document.createElement('button');
//  createAssignmentButton.textContent = 'Create Assignment';
//  createAssignmentButton.addEventListener('click', openAssignmentPopup);
//
//  // Append the button to the content div
//  contentDiv.appendChild(createAssignmentButton);
//}





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


function fetchCourses() {
  const userId = localStorage.getItem('userId');

  fetch(`http://localhost:8080/api/geekconnect/courses/user/${userId}`, {
//  fetch(`http://localhost:8080/api/geekconnect/courses/user/student2`, {
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
    if (data.status === 'OK') {
      renderCourses(data.object);
    } else {
      alert(data.message);
    }
  })
  .catch(error => {
    console.error('Error:', error);
    alert('An error occurred while fetching courses');
  });
}


function renderCourses(courses) {
  const contentDiv = document.getElementById('content');
  contentDiv.innerHTML = '';

  if (courses.length === 0) {
    contentDiv.innerHTML = '<p>No courses found.</p>';
  } else {
    courses.forEach(courseData => {
//      const course = courseData.course;
//      const professor = course.professor;

      // Fetch the course template HTML file
      fetch('course.html')
        .then(response => {
          if (!response.ok) {
            throw new Error('Failed to fetch course template');
          }
          return response.text();
        })
        .then(html => {
          // Replace placeholders with actual data
          html = html.replace('{{courseId}}', courseData.course.id);
          html = html.replace('{{courseName}}',courseData.course.courseName);
          html = html.replace('{{professorFullName}}', courseData.course.professor.fullName);
          html = html.replace('{{professorEmail}}', courseData.course.professor.email);

        // Create a new div and set its content to the assignment HTML
          let courseDiv = document.createElement('div');
          courseDiv.innerHTML = html;
          contentDiv.appendChild(courseDiv);

          courseDiv.addEventListener('click', function() {
             openCourseModal(courseData.course);
           });
        })
        .catch(error => {
          console.error('Error:', error);
          alert('An error occurred while fetching course template');
        });
    });
  }
}

function openCourseModal(course) {
  // Fetch the modal content HTML file
  fetch('content.html')
    .then(response => {
      if (!response.ok) {
        throw new Error('Failed to fetch modal content');
      }
      return response.text();
    })
    .then(html => {
      // Replace placeholders with course details
      html = html.replace('{{courseName}}', course.courseName);
      html = html.replace('{{professorName}}', course.professor.fullName);
      html = html.replace('{{professorEmail}}', course.professor.email);

      // Append modal content to body
      document.body.insertAdjacentHTML('beforeend', html);

      // Get the modal
      let modal = document.querySelector('.modal');

      // Get the close button
      let closeButton = modal.querySelector('.close');

      // Close modal when close button is clicked
      closeButton.addEventListener('click', function() {
        modal.remove();
      });
    })
    .catch(error => {
      console.error('Error:', error);
      alert('An error occurred while fetching modal content');
    });
}


// Function to handle assignment creation form submission
function createAssignment(event) {
    event.preventDefault();

    const assignmentId = document.getElementById('assignmentId').value;
    const assignmentName = document.getElementById('assignmentName').value;
    const description = document.getElementById('description').value;
    const deadline = document.getElementById('deadline').value;
    const courseName = document.getElementById('courseName').value;

    const requestBody = {
        assignmentId: assignmentId,
        assignmentName: assignmentName,
        description: description,
        deadline: deadline,
        course: {
            courseName: courseName
        }
    };

    const userId = localStorage.getItem('userId');

    fetch(`http://localhost:8080/api/geekconnect/${userId}/professor/createassignment/`, {
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
        if (data.status === 'OK') {
            alert('Assignment created successfully!');
            // Close the modal after successful creation
            document.getElementById('assignmentModal').classList.add('hidden');
            // Refresh assignments page after creation
            loadPage('assignment');
        } else {
            alert(data.message);
        }
    })
    .catch(error => {
        console.error('Error:', error);
        alert('An error occurred while creating the assignment');
    });
}

// script.js

// Function to open the assignment creation popup
function openAssignmentPopup() {
  const assignmentModal = document.getElementById('assignmentModal');
  assignmentModal.classList.remove('hidden');
}

// Function to close the assignment creation popup
function closeAssignmentPopup() {
  const assignmentModal = document.getElementById('assignmentModal');
  assignmentModal.classList.add('hidden');
}

// Event listener for the close button in the modal
document.addEventListener('click', function(event) {
  if (event.target.classList.contains('close')) {
    closeAssignmentPopup();
  }
});

// Event listener for form submission
document.getElementById('assignmentForm').addEventListener('submit', function(event) {
  event.preventDefault();

  const assignmentId = document.getElementById('assignmentId').value;
  const assignmentName = document.getElementById('assignmentName').value;
  const description = document.getElementById('description').value;
  const deadline = document.getElementById('deadline').value;
  const courseName = document.getElementById('courseName').value;

  const requestBody = {
    assignmentId: assignmentId,
    assignmentName: assignmentName,
    description: description,
    deadline: deadline,
    course: {
      courseName: courseName
    }
  };

  const userId = localStorage.getItem('userId');

  // Make the POST request to create the assignment
  fetch(`http://localhost:8080/api/geekconnect/${userId}/professor/createassignment/`, {
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
      alert('Assignment created successfully!');
      closeAssignmentPopup(); // Close the popup after successful creation
      // Optionally, you can reload assignments or perform other actions here
    } else {
      alert(data.message); // Display error message if creation failed
    }
  })
  .catch(error => {
    console.error('Error:', error);
    alert('An error occurred while creating the assignment');
  });
});



  
  function logout() {
    // Perform logout actions, e.g., clearing session, redirecting to login page
    alert('Logged out successfully');
    // Redirect to login page
    window.location.href = 'login';
  }
  