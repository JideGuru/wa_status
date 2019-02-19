import 'package:flutter/material.dart';
import 'package:wa_status/ui/home.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {

  var title = "WA Status";
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: "$title",
//      debugShowCheckedModeBanner: false,
      home: Home(
        header: "$title",
      ),

      theme: ThemeData(
        primarySwatch: Colors.green,
      ),
    );
  }
}