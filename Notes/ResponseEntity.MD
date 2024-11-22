# Common `ResponseEntity` Examples in Spring Boot

Below are common HTTP response types (`1xx`, `2xx`, `3xx`, `4xx`, `5xx`) represented with `ResponseEntity` in Spring Boot:

---

## 1xx: Informational Responses
Although Spring Boot typically doesn't use `1xx` responses in a `ResponseEntity`, you can still define custom headers to simulate such cases. These are rare in REST APIs.

```java
// Example: Simulating a 100 Continue Response (Custom Use)
return ResponseEntity.status(HttpStatus.CONTINUE).body("Processing request...");
```

---

## 2xx: Successful Responses

### **200 OK**
Indicates a successful operation with the requested data.

```java
return ResponseEntity.ok("Request was successful!");
```

### **201 Created**
Used after creating a resource, with the new resource or its location.

```java
return ResponseEntity.status(HttpStatus.CREATED).body(newResource);
```

### **204 No Content**
Indicates the operation was successful but doesn't return any body content.

```java
return ResponseEntity.noContent().build();
```

---

## 3xx: Redirection Responses

### **302 Found (Redirection)**
Used to redirect the client to another URI.

```java
return ResponseEntity.status(HttpStatus.FOUND).header("Location", "/new-location").build();
```

### **304 Not Modified**
Indicates the resource has not been modified and can use the cached version.

```java
return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
```

---

## 4xx: Client Errors

### **400 Bad Request**
Indicates invalid or malformed request data.

```java
return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid input data");
```

### **401 Unauthorized**
Indicates that the user is not authenticated.

```java
return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication required");
```

### **403 Forbidden**
Indicates the user is authenticated but does not have permission.

```java
return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied");
```

### **404 Not Found**
Indicates the requested resource was not found.

```java
return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resource not found");
```

---

## 5xx: Server Errors

### **500 Internal Server Error**
Indicates an error occurred on the server.

```java
return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
```

### **503 Service Unavailable**
Indicates the server is temporarily unable to handle the request.

```java
return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("Service is temporarily unavailable");
```

---

## Example Usage of Dynamic Response Handling

```java
public ResponseEntity<?> handleRequest(boolean success) {
    if (success) {
        return ResponseEntity.ok("Operation successful");
    } else {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Operation failed");
    }
}
```

--- 

These `ResponseEntity` examples ensure proper HTTP semantics, improving your API's usability and debugging process.

####
It looks like you're trying to understand the `map` function in Java Streams, particularly in the context of updating a journal entry in your `PUT` method. Let me break this down and clarify how `map` is working here and how you can master it.

---

### **Understanding `map` in This Context**

1. **`Optional.map`**:
    - The `map` function in `Optional` is used to transform the value inside the `Optional` if it exists.
    - It acts as a bridge between a wrapped object (`Optional`) and the logic you want to apply to the object if it's present.

2. **Usage in Your Code**:
    - `journalService.findById(id)` returns an `Optional<JournalEntry>`.
    - The `map` function allows you to "unwrap" this `Optional` and apply a transformation or operation (e.g., updating fields) if the `Optional` contains a value.
    - If the `Optional` is empty, the `map` block is skipped, and the `orElse` block is executed.

---

### **Step-by-Step Explanation of Your Code**

#### **1. Locate the Journal Entry by `id`:**
```java
journalService.findById(id)
```
- This fetches the journal entry wrapped in an `Optional<JournalEntry>`.

#### **2. Use `map` to Process the Entry if Found:**
```java
.map(existingEntry -> {
    // Logic to update fields and save
})
```
- If `findById` finds the entry, the lambda function inside `map` will be executed, where:
    - `existingEntry` is the journal entry retrieved from the database.

#### **3. Update Fields Dynamically:**
```java
existingEntry.setTitle(
    Optional.ofNullable(newEntry.getTitle()// if not null
            .filter(title -> !title.isBlank())// if not blanck
            .orElse(existingEntry.getTitle()));// two are true,then save it orElse execute
```
- This checks if `newEntry.getTitle()`:
    - **Is not null** (`Optional.ofNullable`).
    - **Is not blank** (`filter`).
    - If valid, it updates the title; otherwise, retains the old title.

#### **4. Save the Updated Entry:**
```java
journalService.saveEntry(existingEntry);
```
- The modified `existingEntry` is saved back into the database.

#### **5. Return the Response:**
```java
return new ResponseEntity<>(existingEntry, HttpStatus.OK);
```
- Wrap the updated entry in an HTTP 200 (OK) response.

#### **6. Handle Entry Not Found:**
```java
.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
```
- If the `Optional` is empty (entry not found), return a 404 response.

---

### **How to Learn and Master `map`**

1. **Understand the Concept:**
    - The `map` function applies a transformation to the value inside a wrapper (like `Optional` or a `Stream`).

2. **Practice Simple Examples:**

   **Example 1: Basic Mapping with `Optional`:**
   ```java
   Optional<String> optional = Optional.of("Hello");
   Optional<Integer> length = optional.map(String::length);
   System.out.println(length.orElse(0)); // Output: 5
   ```

   **Example 2: Mapping with a Stream:**
   ```java
   List<String> names = Arrays.asList("John", "Jane", "Jack");
   List<Integer> nameLengths = names.stream()
                                    .map(String::length)
                                    .collect(Collectors.toList());
   System.out.println(nameLengths); // Output: [4, 4, 4]
   ```

3. **Explore More Use Cases:**
    - Use `map` with collections (`Stream` API).
    - Use `map` with nested data structures (e.g., transforming lists of objects).
    - Use `map` in method chains to simplify logic.

4. **Apply in Real-World Scenarios:**
    - Use `map` for conditional updates like your example.
    - Combine `map` with `flatMap` for more complex operations.

---

### **Simplified Notes for `map`**

- **Definition**: `map` transforms elements in a collection or an `Optional`.
- **Works With**: `Stream`, `Optional`, `List`, etc.
- **Key Characteristics**:
    - **Does not modify the original object.**
    - **Always produces a new stream, list, or value.**
- **Usage**:
    - **Transform Values**: Convert an object to another representation (e.g., from `String` to `Integer`).
    - **Conditional Updates**: Apply changes if conditions are met.

---

### **Challenges to Practice**

1. **Transform a List of Names into Uppercase**:
   ```java
   List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
   List<String> uppercasedNames = names.stream()
                                       .map(String::toUpperCase)
                                       .collect(Collectors.toList());
   System.out.println(uppercasedNames); // Output: [ALICE, BOB, CHARLIE]
   ```

2. **Update Multiple Fields in an Object Using `map`:**
    - Try applying your knowledge of `map` in updating fields dynamically like your example.

3. **Handle Nested Optional Fields**:
    - Use `flatMap` when `Optional` returns another `Optional`.

By practicing and experimenting with these cases, you'll develop a strong command over the `map` function and its related functionalities.