# API Documentation

---

## Fetch Spells from External API

### Get All Spells

**URI:** `/api/spells`

**Method:** `GET`

**Description:** Fetches a list of all spells from the external API. If no spells are found, a 404 status is returned.

**Response:**
- **Status Code:**
    - `200 OK` - Successfully retrieved spells.
    - `404 Not Found` - No spells available.

- **Content-Type:** `application/json`
- **Body:** A JSON object containing a list of spells.

---

### Get a Specific Spell by Index

**URI:** `/api/spells/{index}`

**Method:** `GET`

**Description:** Fetches details of a specific spell based on the provided spell index. If the spell is not found, a 404 status is returned.

**Path Parameter:**
- **index**: The index of the spell to be fetched.

**Response:**
- **Status Code:**
    - `200 OK` - Successfully retrieved the spell.
    - `404 Not Found` - The spell with the provided index was not found.

- **Content-Type:** `application/json`
- **Body:** A JSON object containing the details of the spell.

---

### Fetch All Spells Details

**URI:** `/api/spells/fetch-all`

**Method:** `GET`

**Description:** Fetches detailed information for all spells. If no spells are available, a 204 status is returned.

**Response:**
- **Status Code:**
    - `200 OK` - Successfully retrieved all spells.
    - `204 No Content` - No spells available.

- **Content-Type:** `application/json`
- **Body:** A JSON array of spell details, or empty if no spells are found.

---

### Error Handling

In case of errors while fetching data from the external API:
- If there is an issue with the external API, a `502 Bad Gateway` status may be returned.
- If the requested spell is not found, a `404 Not Found` status will be returned.
- Any other exceptions encountered will be handled and may also return a `500 Internal Server Error` status.

---