package com.example.bracket.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping ("/api/admin")
public class AdminController {

  private final Path bracketFile = Paths.get("bracket.json");

  @PostMapping("/bracket")
  public ResponseEntity<?> saveBracket(@RequestBody String json) {
    try {
      Files.write(bracketFile, json.getBytes(StandardCharsets.UTF_8));
      return ResponseEntity.ok().build();
    } catch (IOException e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save bracket");
    }
  }

  @GetMapping ("/bracket")
  public ResponseEntity<String> getBracket() {
    try {
      String content = Files.readString(bracketFile);
      return ResponseEntity.ok(content);
    } catch (IOException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{}");
    }
  }
}

