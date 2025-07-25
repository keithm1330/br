package com.example.bracket.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
public class BracketController {

  private static final String BRACKET_FILE = "bracket.json";

  @GetMapping("/api/bracket")
  public String getBracket() throws Exception {
    return Files.readString(Paths.get(BRACKET_FILE));
  }
}

