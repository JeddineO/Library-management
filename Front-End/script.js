window.onload = fetchUsers;

function fetchUsers() {
    fetch('http://localhost:8080/livres')
        .then(response => response.json())
        .then(livres => {
            console.log(livres);
            document.getElementById('userTableBody').innerHTML = '';

            livres.forEach(livre => {
                const row = `
                    <tr>
                        <td>${livre.isbn}</td>
                        <td>${livre.nom}</td>
                        <td>${livre.auteur}</td>
                        <td>${livre.prix}</td>
                        <td>${livre.disponible}</td>
                        <td>
                        <button class="btn btn-primary btn-sm" onclick="editUser(${livre.isbn},'${livre.nom}','${livre.auteur}',${livre.prix})">Edit</button>
                        <button class="btn btn-danger btn-sm" onclick="deleteUser(${livre.isbn})">Delete</button>
                        <button class="btn btn-success btn-sm" onclick="emprunt(${livre.isbn})">Emprunter</button>
                        </td>
                    </tr>
                `;
                document.getElementById('userTableBody').innerHTML += row;
            });
        })
        .catch(error => console.error('Error fetching users:', error));
}

function deleteUser(id) {
    if (confirm('Confirmer la supprusion?')) {
        fetch(`http://localhost:8080/livre/${id}`, {
            method: 'DELETE'
        })
            .then(response => response.json())
            .then(deletedUser => {
                alert('User deleted successfully');
                fetchUsers();
            })
            .catch(error => console.error('Error deleting user:', error));
    }
}

function editUser(id, name, email, age) {

    $('#myModal').on('shown.bs.modal', function () {
        $('#myInput').trigger('focus')
    })
    console.log("hello");
    $('#editUserId').val(id);
    $('#editUserName').val(name);
    $('#editUserEmail').val(email);
    $('#editUserAge').val(age);
    $('#editUserModal').css('display', 'block');
}

function updateUser() {
    console.log("test");

    var id = $('#editUserId').val();

    $('#addUserForm').on('submit', function (event) {
        event.preventDefault();
        const formData = new FormData(this);
        const userData = {};
        formData.forEach((value, key) => {
            userData[key] = value;
        });

        console.log(userData);

        // Send PUT request to update user
        fetch(`http://localhost:8080/livre/${id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(userData)
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.json();
            })
            .then(addedUser => {
                alert('User bien modifié');
                $('#addUserForm')[0].reset();
                fetchUsers();
            })
            .catch(error => {
                console.error('Error adding user:', error);
            });
    });
    $('#editUserModal').css('display', 'none');

}

function closeEdit() {
    $('#editUserModal').css('display', 'none');
}


