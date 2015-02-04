package controllers

import play.api._
import play.api.mvc._
import scala.slick.driver.MySQLDriver.simple._
import scala.slick.lifted.ProvenShape

case class Member(id: Int, name: String, email: String, avatar: String, position: Int)

class Members(tag: Tag) extends Table[(Int, String, String, String, Int)](tag,"member") {
  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
  def name = column[String]("name", O.NotNull)
  def email = column[String]("email", O.NotNull)
  def avatar = column[String]("avatar")
  def position = column[Int]("position", O.NotNull)
  def * = (id, name, email, avatar, position)
}

object Application extends Controller {

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  def insertMember = Action {
    val members = TableQuery[Members]
    val db = Database.forURL(
      "jdbc:mysql://us-cdbr-iron-east-01.cleardb.net/heroku_3527ddcd678c81b?reconnect=true",
      "bab3372bccec58",
      "f200ff41",
      null,
      "com.mysql.jdbc.Driver"
    )
    db.withSession { implicit session =>
      members +=(1, "Mackenzie Marshall", "mackenzie.marshall@hootsuite.com", null, 1)
    }

    Ok("We're good")
  }

}