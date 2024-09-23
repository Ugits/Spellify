# CLIENT DOCUMENTATION

---

## Retrieve a List of All Spells
**Endpoint**: `GET /spells`

**Description**: Fetches a list of all available spells.

**Response**: Returns a list of spell objects. Throws `SpellNotFoundException` if no spells are found.

---

## Retrieve a Spell by Name
**Endpoint**: `GET /spells/{name}`

**Description**: Fetches a specific spell by its name. The search is case-insensitive.

**Path Parameters**:
- `name` (String): Name of the spell.

**Validation**: The spell name is validated for non-null and non-empty values.

**Response**: Returns a spell object. Throws `SpellNotFoundException` if the spell is not found.

---

## Retrieve Spells by Level
**Endpoint**: `GET /spells/level/{level}`

**Description**: Retrieves all spells that match the specified spell level.

**Path Parameters**:
- `level` (Integer): Spell level, between 0 and 9.

**Validation**: The level is validated using annotations to ensure it's between 0 and 9. Throws `SpellValidationException` if the level is out of range.

**Response**: Returns a list of spells. Throws `SpellNotFoundException` if no spells are found for the given level.

---

## Retrieve Spells Usable by a Character of a Given Level
**Endpoint**: `GET /spells/usable/{level}`

**Description**: Retrieves spells that a character can cast at or below the specified level. For example, if a character is level 5, all spells from level 0 to level 5 will be returned.

**Path Parameters**:
- `level` (Integer): The maximum spell level a character can cast, between 0 and 9.

**Validation**: The level is validated to ensure it falls within 0 and 9. Throws `SpellValidationException` for invalid levels.

**Response**: Returns a list of spells that can be cast by a character of the given level. Throws `SpellNotFoundException` if no matching spells are found.

---

## Retrieve Spells Based on Ritual Casting (Optional: Filter by Maximum Level)
**Endpoint**: `GET /spells/ritual/{ritual}`

**Description**: Retrieves spells based on whether they can be cast as a ritual. Optionally, limit the results by specifying a maximum spell level.

**Path Parameters**:
- `ritual` (Boolean): Whether the spell can be cast as a ritual (`true` or `false`).

**Query Parameters (Optional)**:
- `max-level` (Integer): Maximum spell level to return (default is 9).

**Validation**: Both `ritual` and `max-level` parameters are validated for correctness. Throws `SpellValidationException` for invalid input.

**Response**: Returns a list of ritual spells up to the specified level. Throws `SpellNotFoundException` if no spells match the criteria.

---

## Retrieve Spells by Casting Time (Optional: Filter by Maximum Level)
**Endpoint**: `GET /spells/casting-time/{castingTime}`

**Description**: Retrieves spells based on the specified casting time. Optionally, limit the results by specifying a maximum spell level.

**Path Parameters**:
- `castingTime` (String): The required casting time of the spell.

**Query Parameters (Optional)**:
- `max-level` (Integer): Maximum spell level to return (default is 9).

**Validation**: Both the `castingTime` and `max-level` are validated to ensure valid inputs. Throws `SpellValidationException` for invalid values.

**Valid Casting Time Options**:
- `1 action`
- `1 bonus action`
- `1 reaction`
- `1 minute`
- `10 minutes`
- `1 hour`
- `8 hours`
- `12 hours`
- `24 hours`

**Response**: Returns a list of spells matching the casting time criteria. Throws `SpellNotFoundException` if no spells match.

---

## Retrieve Spells Based on Concentration Requirement (Optional: Filter by Maximum Level)
**Endpoint**: `GET /spells/concentration/{concentration}`

**Description**: Retrieves spells based on whether they require concentration. Optionally, limit the results by specifying a maximum spell level.

**Path Parameters**:
- `concentration` (Boolean): Whether the spell requires concentration (`true` or `false`).

**Query Parameters (Optional)**:
- `max-level` (Integer): Maximum spell level to return (default is 9).

**Validation**: The `concentration` and `max-level` values are validated for correctness. Throws `SpellValidationException` for invalid inputs.

**Response**: Returns a list of spells that require concentration up to the specified level. Throws `SpellNotFoundException` if no spells match the criteria.

---

## Retrieve Spells by Duration (Optional: Filter by Maximum Level)
**Endpoint**: `GET /spells/duration/{duration}`

**Description**: Retrieves spells based on their duration. Optionally, limit the results by specifying a maximum spell level.

**Path Parameters**:
- `duration` (String): The duration of the spell.

**Query Parameters (Optional)**:
- `max-level` (Integer): Maximum spell level to return (default is 9).

**Validation**: The `duration` and `max-level` parameters are validated to ensure valid inputs. Throws `SpellValidationException` for invalid input.

**Valid Duration Options**:
- `1 round`
- `1 minute`
- `10 minutes`
- `1 hour`
- `8 hours`
- `24 hours`
- `7 days`
- `10 days`
- `30 days`
- `Instantaneous`
- `Special`
- `Until dispelled`
- `Up to 1 round`
- `Up to 1 minute`
- `Up to 10 minutes`
- `Up to 1 hour`
- `Up to 8 hours`
- `Up to 24 hours`

**Response**: Returns a list of spells with the given duration. Throws `SpellNotFoundException` if no spells match.

---

## Retrieve Spells by Range (Optional: Filter by Maximum Level)
**Endpoint**: `GET /spells/range/{range}`

**Description**: Retrieves spells based on their range. Optionally, limit the results by specifying a maximum spell level.

**Path Parameters**:
- `range` (String): The range of the spell.

**Query Parameters (Optional)**:
- `max-level` (Integer): Maximum spell level to return (default is 9).

**Validation**: The `range` and `max-level` values are validated to ensure valid inputs. Throws `SpellValidationException` for invalid input.

**Valid Range Options**:
- `5 feet`
- `10 feet`
- `30 feet`
- `60 feet`
- `90 feet`
- `100 feet`
- `120 feet`
- `150 feet`
- `300 feet`
- `500 feet`
- `1 mile`
- `500 miles`
- `Self`
- `Sight`
- `Special`
- `Touch`
- `Unlimited`

**Response**: Returns a list of spells that match the specified range. Throws `SpellNotFoundException` if no spells match.

---
