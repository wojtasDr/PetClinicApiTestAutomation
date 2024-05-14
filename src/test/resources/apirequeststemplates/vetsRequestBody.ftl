{
    "firstName": "${(firstName)}",
    "lastName": "${(lastName)}",
    "specialties": [{
        "name": "${(specialtyName)?default("radiology")}"
    }]
}