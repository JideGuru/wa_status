import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'dart:async';
import 'dart:io';

class Home extends StatefulWidget {
  final String header;

  Home({Key key, this.header}) : super(key: key);
  @override
  _HomeState createState() => _HomeState();
}

class _HomeState extends State<Home> {
  static const platform = const MethodChannel('samples.flutter.io/files');
  List imgs;


  Future<void> _getImages() async {
    List images;
    try {
      final List result = await platform.invokeMethod('getImages');
      images = result;
      print(images);
    } on PlatformException catch (e) {
      print("Error");
//      images = "Something went wrong";

    }

    setState(() {
      imgs = images;
    });
  }


  @override
  void initState() {
    _getImages();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(

      backgroundColor: Colors.white,

      appBar: AppBar(
        title: Text(
          widget.header,
          style: TextStyle(
            color: Colors.white,
          ),
        ),

        actions: <Widget>[
//          IconButton(
//              icon:Icon(
//                Icons.info_outline,
//                color: Colors.white,
//              ),
//              onPressed: () => _showAlertInfo(context)
//          ),
        ],
      ),



      body: Container(
        child: Column(
          children: <Widget>[

            Text("hi"),
          ],
        ),
      ),
    );
  }
}
