// Fetch users on page load
document.addEventListener('DOMContentLoaded', function() {
    fetchusers();
});

let usersData = []; // Variable global para almacenar los datos de nóminas

function fetchusers(order = 'asc') {
    axios.get('http://localhost:8082/user/getall')
        .then(response => {
            usersData = response.data;

            // Ordenar datos en función del parámetro "order"
            usersData.sort((a, b) => {
                if (order === 'asc') {
                    return a.code - b.code;
                } else {
                    return b.code - a.code;
                }
            });

            // Renderizar tabla
            renderTable(usersData);
        })
        .catch(error => {
            console.error("Error fetching users:", error);
            alert("An error occurred while fetching user data.");
        });
}

function sortTable(order) {
    fetchusers(order); // Recargar los datos en el orden solicitado
}

function renderTable(users) {
    const tableBody = document.getElementById('userTableBody');
    tableBody.innerHTML = '';
    users.forEach(user => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${user.code}</td>
            <td>${user.employeeName}</td>
            <td>${user.department}</td>
            <td>${user.position}</td>
            <td>${new Date(user.hireDate).toLocaleDateString()}</td>
            <td>${user.healthInsurance}</td>
            <td>${user.occupationalRiskInsurance}</td>
            <td>${user.pension}</td>
            <td>${user.salary.toFixed(2)}</td>
            <td>
                <a href="#edituserModal" class="edit" data-toggle="modal" onclick="openEditModal(${user.code})"><i class="material-icons" data-toggle="tooltip" title="Edit"></i></a>
                <a href="#deleteuserModal" class="delete" data-toggle="modal" onclick="openDeleteModal(${user.code})"><i class="material-icons" data-toggle="tooltip" title="Delete"></i></a>
            </td>
        `;
        tableBody.appendChild(row);
    });
}

// Handle form submission for adding user
document.getElementById('adduserForm').addEventListener('submit', function(event) {
    event.preventDefault(); // Prevent default form submission

    // Get form values
    const code = document.getElementById('addCode').value;
    const employeeName = document.getElementById('addEmployeeName').value;
    const department = document.getElementById('addDepartment').value;
    const position = document.getElementById('addPosition').value;
    const hireDate = document.getElementById('addHireDate').value;
    const healthInsurance = document.getElementById('addHealthInsurance').value;
    const riskInsurance = document.getElementById('addRiskInsurance').value;
    const pension = document.getElementById('addPension').value;
    const salary = document.getElementById('addSalary').value;

    // Validate fields
    if (!code || !employeeName || !department || !position || !hireDate || !healthInsurance || !riskInsurance || !pension || !salary) {
        alert("Please fill in all fields.");
        return;
    }

    // Make API call to create user
    axios.post(`http://localhost:8082/user/create?code=${code}&employeeName=${employeeName}&department=${department}&position=${position}&hireDate=${hireDate}&healthInsurance=${healthInsurance}&occupationalRiskInsurance=${riskInsurance}&pension=${pension}&salary=${parseFloat(salary)}`)
    .then(response => {
        alert(response.data); // Display success message
        $('#adduserModal').modal('hide'); // Hide modal
        fetchusers(); // Refresh user list
    })
    .catch(error => {
        if (error.response) {
            alert("Error: " + error.response.data);
        } else {
            console.error("Error creating user:", error);
            alert("An error occurred while adding the user.");
        }
    });
});

function openEditModal(code) {
    axios.get(`http://localhost:8082/user/getbyid/${code}`)
        .then(response => {
            const user = response.data;

            // Eliminar el formulario anterior si existe
            const existingForm = document.getElementById('dynamicEditForm');
            if (existingForm) {
                existingForm.remove();
            }

            // Crear un nuevo formulario
            const form = document.createElement('form');
            form.id = 'dynamicEditForm';
            form.innerHTML = `
                <div class="form-group">
                    <label>Code</label>
                    <input type="text" class="form-control" id="editCode" value="${user.code}" readonly>
                </div>
                <div class="form-group">
                    <label>Employee Name</label>
                    <input type="text" class="form-control" id="editEmployeeName" value="${user.employeeName}" required>
                </div>
                <div class="form-group">
                    <label>Department</label>
                    <input type="text" class="form-control" id="editDepartment" value="${user.department}" required>
                </div>
                <div class="form-group">
                    <label>Position</label>
                    <input type="text" class="form-control" id="editPosition" value="${user.position}" required>
                </div>
                <div class="form-group">
                    <label>Hire Date</label>
                    <input type="date" class="form-control" id="editHireDate" value="${new Date(user.hireDate).toISOString().split('T')[0]}" required>
                </div>
                <div class="form-group">
                    <label>Health Insurance</label>
                    <input type="text" class="form-control" id="editHealthInsurance" value="${user.healthInsurance}" required>
                </div>
                <div class="form-group">
                    <label>Risk Insurance</label>
                    <input type="text" class="form-control" id="editRiskInsurance" value="${user.occupationalRiskInsurance}" required>
                </div>
                <div class="form-group">
                    <label>Pension</label>
                    <input type="text" class="form-control" id="editPension" value="${user.pension}" required>
                </div>
                <div class="form-group">
                    <label>Salary</label>
                    <input type="number" class="form-control" id="editSalary" value="${user.salary}" required>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                    <button type="submit" class="btn btn-success">Update</button>
                </div>
            `;

            // Añadir el listener al nuevo formulario para manejar la actualización
            form.addEventListener('submit', function(event) {
                event.preventDefault();
                updateuser(code);
            });

            // Insertar el formulario en el modal o en el contenedor correspondiente
            const modalBody = document.querySelector('#edituserModal .modal-body');
            modalBody.appendChild(form);

            // Mostrar el modal
            $('#edituserModal').modal('show');
        })
        .catch(error => {
            console.error("Error fetching user by Code:", error);
            alert("Error retrieving user details.");
        });
}

function updateuser(code) {
    // Obtener valores del formulario
    const employeeName = document.getElementById('editEmployeeName').value;
    const department = document.getElementById('editDepartment').value;
    const position = document.getElementById('editPosition').value;
    const hireDate = document.getElementById('editHireDate').value;
    const healthInsurance = document.getElementById('editHealthInsurance').value;
    const riskInsurance = document.getElementById('editRiskInsurance').value;
    const pension = document.getElementById('editPension').value;
    const salary = parseFloat(document.getElementById('editSalary').value);

    // Enviar los datos actualizados a la API
    axios.put(`http://localhost:8082/user/update`, null, {
        params: {
            code,
            employeeName,
            department,
            position,
            hireDate,
            healthInsurance,
            occupationalRiskInsurance: riskInsurance,
            pension,
            salary
        }
    })
    .then(response => {
        alert(response.data); // Mostrar mensaje de éxito
        $('#edituserModal').modal('hide'); // Cerrar el modal
        fetchusers(); // Refrescar la lista de nóminas
    })
    .catch(error => {
        console.error("Error updating user:", error);
        alert("Error updating user.");
    });
}

let employeeToDelete = null; // Variable global para almacenar el código del empleado a eliminar

function openDeleteModal(code) {
    employeeToDelete = code; // Guarda el código del empleado a eliminar
    $('#deleteEmployeeModal').modal('show'); // Muestra el modal de confirmación
}

function confirmDeleteEmployee() {
    if (employeeToDelete) {
        axios.delete(`http://localhost:8082/user/deletebyid/${employeeToDelete}`)
            .then(response => {
                alert(response.data);
                $('#deleteEmployeeModal').modal('hide'); // Oculta el modal
                fetchusers(); // Actualiza la tabla
            })
            .catch(error => {
                console.error("Error deleting user:", error);
                alert("Error deleting user.");
            });
    }
}


function searchEmployee() {
    const code = document.getElementById('searchCode').value.trim();

    if (!code) {
        alert("Please enter a code to search.");
        return;
    }

    axios.get(`http://localhost:8082/user/getbyid/${code}`)
        .then(response => {
            const user = response.data;
            
            // Limpiar el cuerpo de la tabla y mostrar solo el empleado encontrado
            const tableBody = document.getElementById('userTableBody');
            tableBody.innerHTML = '';

            const row = document.createElement('tr');
            row.innerHTML = `
                <td>${user.code}</td>
                <td>${user.employeeName}</td>
                <td>${user.department}</td>
                <td>${user.position}</td>
                <td>${new Date(user.hireDate).toLocaleDateString()}</td>
                <td>${user.healthInsurance}</td>
                <td>${user.occupationalRiskInsurance}</td>
                <td>${user.pension}</td>
                <td>${user.salary.toFixed(2)}</td>
                <td>
                    <a href="#edituserModal" class="edit" data-toggle="modal" onclick="openEditModal(${user.code})"><i class="material-icons" data-toggle="tooltip" title="Edit"></i></a>
                    <a href="#deleteuserModal" class="delete" data-toggle="modal" onclick="openDeleteModal(${user.code})"><i class="material-icons" data-toggle="tooltip" title="Delete"></i></a>
                </td>
            `;
            tableBody.appendChild(row);
        })
        .catch(error => {
            console.error("Error fetching employee by code:", error);
            alert("Employee not found.");
        });
}

//----------------------------------------

function uploadExcel() {
    const fileInput = document.getElementById("excelFile");

    // Verificar si se ha seleccionado un archivo
    if (fileInput.files.length === 0) {
        alert("Please select an Excel file to upload.");
        return;
    }

    const file = fileInput.files[0];
    const formData = new FormData();
    formData.append("file", file);

    // Llamada al backend para subir el archivo y crear las nóminas
    axios.post("http://localhost:8082/user/createwithexcel", formData, {
        headers: {
            "Content-Type": "multipart/form-data"
        }
    })
    .then(response => {
        alert("users created successfully from Excel file!");
        fetchusers(); // Recargar la lista de nóminas
    })
    .catch(error => {
        console.error("Error uploading Excel file:", error);
        alert("There was an error uploading the file or creating users.");
    });
}


function generatePDF() {
    const { jsPDF } = window.jspdf;
    const doc = new jsPDF();

    doc.text("Employee user List", 14, 10);

    const headers = [["Code", "Employee Name", "Department", "Position", "Hire Date", "Health Insurance", "Risk Insurance", "Pension", "Salary"]];
    const data = usersData.map(user => [
        user.code,
        user.employeeName,
        user.department,
        user.position,
        new Date(user.hireDate).toLocaleDateString(),
        user.healthInsurance,
        user.occupationalRiskInsurance,
        user.pension,
        user.salary.toFixed(2)
    ]);

    doc.autoTable({
        head: headers,
        body: data,
        startY: 20,
        theme: 'striped'
    });

    doc.save("Employee_user_List.pdf");
}

function renderCharts() {
    // Gráfico de Torta: Cantidad de empleados por departamento
    const departments = usersData.reduce((acc, user) => {
        acc[user.department] = (acc[user.department] || 0) + 1;
        return acc;
    }, {});

    const departmentPieChart = new Chart(document.getElementById('departmentPieChart'), {
        type: 'pie',
        data: {
            labels: Object.keys(departments),
            datasets: [{
                data: Object.values(departments),
                backgroundColor: ['#FF6384', '#36A2EB', '#FFCE56', '#4BC0C0']
            }]
        },
        options: {
            responsive: true,
            plugins: {
                legend: {
                    position: 'top',
                }
            }
        }
    });

    // Gráfico de Barras: Cantidad de empleados por posición
    const positions = usersData.reduce((acc, user) => {
        acc[user.position] = (acc[user.position] || 0) + 1;
        return acc;
    }, {});

    const positionBarChart = new Chart(document.getElementById('positionBarChart'), {
        type: 'bar',
        data: {
            labels: Object.keys(positions),
            datasets: [{
                label: 'Number of Employees',
                data: Object.values(positions),
                backgroundColor: '#36A2EB'
            }]
        },
        options: {
            responsive: true,
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });
}

// Llama a renderCharts después de cargar los datos
fetchusers();
renderCharts();

function generatePositionPDF() {
    const { jsPDF } = window.jspdf;
    const doc = new jsPDF("landscape", "mm", "a3");

    doc.text("Employee Count by Position", 14, 10);

    const positions = usersData.reduce((acc, user) => {
        acc[user.position] = (acc[user.position] || 0) + 1;
        return acc;
    }, {});

    const positionNames = Object.keys(positions);
    const positionCounts = Object.values(positions);

    let startY = 20;
    positionNames.forEach((position, index) => {
        const barWidth = positionCounts[index] * 10;
        const barColor = `#${Math.floor(Math.random()*16777215).toString(16)}`;

        doc.setFillColor(barColor);
        doc.rect(20, startY, barWidth, 10, "F");
        doc.text(`${position} (${positionCounts[index]})`, barWidth + 25, startY + 7);

        startY += 15;
    });

    doc.save("Position_Bar_Chart.pdf");
}

function generateDepartmentPDF() {
    const { jsPDF } = window.jspdf;
    const doc = new jsPDF();

    // Título del PDF
    doc.text("Employee Count by Department", 14, 10);

    // Obtener los datos de departamentos y contar empleados
    const departments = usersData.reduce((acc, user) => {
        acc[user.department] = (acc[user.department] || 0) + 1;
        return acc;
    }, {});

    const departmentNames = Object.keys(departments);
    const departmentCounts = Object.values(departments);
    const totalEmployees = departmentCounts.reduce((sum, count) => sum + count, 0);

    // Configuración del gráfico circular
    const centerX = 105;
    const centerY = 80;
    const radius = 60;
    let startAngle = 0;

    // Colores fijos para cada departamento
    const colors = [
        "#4B0082", "#FF00FF", "#40E0D0", "#FFD700", "#FF4500", "#7FFF00", "#1E90FF", "#FF1493", "#8B4513", "#2E8B57",
        "#4682B4", "#D2691E", "#556B2F", "#8A2BE2", "#FF6347", "#00CED1", "#00FA9A", "#ADFF2F", "#6A5ACD", "#FF7F50",
        "#20B2AA", "#DC143C", "#32CD32", "#8B0000", "#00008B", "#BDB76B", "#A52A2A", "#5F9EA0", "#9932CC", "#FF69B4"
    ]; // Ajusta los colores según prefieras

    // Dibujar los segmentos del gráfico circular
    departmentCounts.forEach((count, index) => {
        const sliceAngle = (count / totalEmployees) * 2 * Math.PI;
        const endAngle = startAngle + sliceAngle;

        // Definir el color de cada segmento
        doc.setFillColor(colors[index % colors.length]);

        // Dibujar el sector en pequeños segmentos usando un bucle
        let angle = startAngle;
        const step = 0.01; // Define el paso para aproximar el arco
        doc.moveTo(centerX, centerY);
        while (angle <= endAngle) {
            const x = centerX + radius * Math.cos(angle);
            const y = centerY + radius * Math.sin(angle);
            doc.lineTo(x, y);
            angle += step;
        }
        doc.lineTo(centerX, centerY); // Cerrar el sector
        doc.fill();

        // Actualizar el ángulo de inicio para el siguiente sector
        startAngle = endAngle;
    });

    // Dibujar el contorno del gráfico circular
    doc.setDrawColor(0, 0, 0); // Color de borde (negro)
    doc.circle(centerX, centerY, radius); // Dibuja el círculo completo como contorno

    // Leyenda
    let legendY = 160;
    departmentNames.forEach((dept, index) => {
        doc.setFillColor(colors[index % colors.length]);
        doc.rect(20, legendY, 5, 5, "F");
        doc.text(`${dept} (${departments[dept]})`, 30, legendY + 4);
        legendY += 10;
    });

    // Guardar el PDF
    doc.save("Department_Pie_Chart.pdf");
}