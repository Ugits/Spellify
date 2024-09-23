# ADMIN SPELL DOCUMENTATION

---

### Add a List of Spells (Batch)

**URI:** `/admin/spells/add-batch`

**Method:** `POST`

**Description:** Adds a batch of new spells to the repository. This operation will not replace existing spells. If any spell in the batch already exists (based on its index), the operation will return an error with details on which spells were already present.

**Request Body:**
- **Content-Type:** `application/json`
- **Body:** A JSON array of `SpellDTO` objects representing the spells to be added.

**Response:**
- **Status Code:**
  - `201 Created` if all spells were successfully added.
  - `400 Bad Request` if any spell in the batch fails validation.
  - `409 Conflict` if one or more spells already exist.
- **Content-Type:** `application/json`
- **Body:** A JSON array of the successfully added spells or an error message.

---

### Add a Single Spell

**URI:** `/admin/spells/add`

**Method:** `POST`

**Description:** Adds a single new spell to the repository. This operation will fail if a spell with the same index already exists.

**Request Body:**
- **Content-Type:** `application/json`
- **Body:** A `SpellDTO` object representing the spell to be added.

**Response:**
- **Status Code:**
  - `201 Created` if the spell was successfully added.
  - `400 Bad Request` if the spell fails validation.
  - `409 Conflict` if a spell with the same index already exists.
- **Content-Type:** `application/json`
- **Body:** The newly created spell or an error message.

---

### Sync Spells from External API

**URI:** `/admin/spells/sync-api`

**Method:** `PUT`

**Description:** Syncs the spells from an external API and updates or adds them to the repository. Spells that already exist will be updated with the new data.

**Response:**
- **Status Code:**
  - `200 OK` if the sync was successful.
  - `502 Bad Gateway` if there is an error communicating with the external API (e.g., the API is down or unreachable).
- **Content-Type:** `application/json`
- **Body:** A JSON array of the updated or newly added spells, or an error message.

---

### Update a Specific Spell by Index

**URI:** `/admin/spells/{index}`

**Method:** `PATCH`

**Description:** Updates an existing spell based on its index with the fields provided in the request body. Only the fields provided in the body will be updated.

**Path Parameter:**
- **index**: The unique index of the spell to be updated.

**Request Body:**
- **Content-Type:** `application/json`
- **Body:** An `UpdateSpellDTO` object with the fields to be updated.

**Response:**
- **Status Code:**
  - `200 OK` if the spell was successfully updated.
  - `400 Bad Request` if the spell fails validation.
  - `404 Not Found` if no spell with the specified index exists.
- **Content-Type:** `application/json`
- **Body:** The updated spell or an error message.

---

### Truncate (Delete All Spells)

**URI:** `/admin/spells/truncate`

**Method:** `DELETE`

**Description:** Deletes all spells from the repository, along with their related spell descriptions. This operation resets the spell database.

**Response:**
- **Status Code:** `204 No Content` if the operation was successful.
- **Content-Type:** `application/json`
- **Body:** Empty.

---

### Delete a Specific Spell by ID

**URI:** `/admin/spells/{id}`

**Method:** `DELETE`

**Description:** Deletes a specific spell from the repository based on its ID. If the spell with the provided ID does not exist, a 404 status is returned.

**Path Parameter:**
- **id**: The ID of the spell to be deleted.

**Response:**
- **Status Code:**
  - `204 No Content` if the spell was successfully deleted.
  - `404 Not Found` if no spell with the provided ID exists.
- **Content-Type:** `application/json`
- **Body:** Empty or an error message.

---

### Error Handling

- **400 Bad Request**: Returned when a request body is invalid or fails validation (e.g., missing required fields).
- **404 Not Found**: Returned when trying to access or delete a spell that doesn't exist.
- **409 Conflict**: Returned when trying to add a spell that already exists in the repository.
- **502 Bad Gateway**: Returned when there is an issue communicating with the external API during a sync operation.

---
