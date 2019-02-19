import 'dart:core';

import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'dart:async';
import 'package:transparent_image/transparent_image.dart';
import 'dart:io';


class Home extends StatefulWidget {
  final String header;

  Home({Key key, this.header}) : super(key: key);
  @override
  _HomeState createState() => _HomeState();
}

class _HomeState extends State<Home> {
  //Channel Platform: Check MainActivity.java
  static const platform = const MethodChannel('samples.flutter.io/files');
  List imgs;



  //Check the MainActivity.java
  Future<void> _getImages() async {
    List images;
    try {
      final List result = await platform.invokeMethod('getImages');
      images = result;
    } on PlatformException catch (e) {
      print("Error");
    }

    setState(() {
      imgs = images;
    });
  }


  @override
  void initState() {
    _getImages();
  }


  List<Widget> createImageCardItem(
      List images, BuildContext context) {
    // Children list for the list.
    List<Widget> listElementWidgetList = new List<Widget>();
    if (images != null) {
      var lengthOfList = images.length;
      for (int i = 0; i < lengthOfList; i++) {
        String img_src = "/storage/emulated/0/GBWhatsApp/Media/.Statuses/"+images[i];
        String img_name = images[i];
        File img_file = new File(img_src);
        print(img_name);
        // Image URL
        // List item created with an image of the poster
        var listItem = GridTile(
            footer: GridTileBar(
              backgroundColor: Colors.black45,
              title: Text(img_name),
            ),
            child: GestureDetector(
              onTap: () {
                var router = MaterialPageRoute(
                    builder: (BuildContext context){
//                      return Details(header: movie.title, img: imageURL, id: movie.id);
                    }
                );

                Navigator.of(context).push(router);
              },

              child: Image.file(img_file),
//              child: FadeInImage.(
//                placeholder: "$kTransparentImage",
//                image: img_src,
//                fit: BoxFit.cover,
//              ),
            ));
        listElementWidgetList.add(listItem);
      }
    }
    return listElementWidgetList;
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



      body: CustomScrollView(
        primary: false,
        slivers: <Widget>[
          new SliverPadding(
            padding: const EdgeInsets.all(10.0),
            sliver: new SliverGrid.count(
              crossAxisSpacing: 10.0,
              mainAxisSpacing: 10.0,
              crossAxisCount: 2,
              children:
              createImageCardItem(imgs, context),
            ),
          ),
        ],
      ),
    );
  }
}
